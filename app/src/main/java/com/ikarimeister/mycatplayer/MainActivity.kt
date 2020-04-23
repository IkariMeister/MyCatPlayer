package com.ikarimeister.mycatplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ikarimeister.mycatplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MediaItemAdapter(getItems()) { toast(it.title) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.adapter = adapter
    }
}