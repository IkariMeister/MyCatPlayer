package com.ikarimeister.mycatplayer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ikarimeister.mycatplayer.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        MediaItemAdapter {
            startActivity<DetailActivity>(Pair(DetailActivity.EXTRA_ID, it.id))
        }
    }

    private fun loadData(f: (List<MediaItem>) -> Unit) = lifecycleScope.launch {
        binding.progress.visibility = View.VISIBLE
        val elements = withContext(Dispatchers.IO) { async { MediaProvider.getItems() } }
        f(elements.await())
        binding.progress.visibility = View.GONE
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.adapter = adapter
        loadData { adapter.items = it }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @UiThread
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        loadData { items ->
            adapter.items = items.let { media ->
                when (item.itemId) {
                    R.id.filter_all -> media
                    R.id.filter_photos -> media.filter { it.type == MediaType.PHOTO }
                    R.id.filter_videos -> media.filter { it.type == MediaType.VIDEO }
                    else -> media
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}