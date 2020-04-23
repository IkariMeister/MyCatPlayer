package com.ikarimeister.mycatplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ikarimeister.mycatplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = MediaItemAdapter()
        binding.recycler.adapter = adapter
        adapter.items = getItems()
    }
}