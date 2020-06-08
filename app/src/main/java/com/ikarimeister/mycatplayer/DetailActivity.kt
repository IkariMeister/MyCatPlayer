package com.ikarimeister.mycatplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ikarimeister.mycatplayer.databinding.ActivityDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "ACTIVITY_DETAIL_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            val items = withContext(Dispatchers.IO) { MediaProvider.getItems() }
            items.firstOrNull { it.id == intent.extras?.getInt(EXTRA_ID) }
                    ?.let {
                        supportActionBar?.title = it.title
                        binding.detailThumb.loadUrl(it.url)
                        binding.detailVideoIndicator.visibility =
                                when (it.type) {
                                    MediaType.VIDEO -> View.VISIBLE
                                    MediaType.PHOTO -> View.GONE
                                }
                    }
        }
    }
}