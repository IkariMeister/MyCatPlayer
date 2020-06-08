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
        val filter = when (item.itemId) {
            R.id.filter_all -> NoFilter
            R.id.filter_photos -> TypeFilter(MediaType.PHOTO)
            R.id.filter_videos -> TypeFilter(MediaType.VIDEO)
            else -> NoFilter
        }
        updateFilter(filter)
        return super.onOptionsItemSelected(item)
    }

    fun updateFilter(filter: Filter) {
        loadData {
            adapter.items = it.let {
                when (filter) {
                    NoFilter -> it
                    is TypeFilter -> it.filter { it.type == filter.type }
                }
            }
        }
    }
}