package com.ikarimeister.mycatplayer

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MediaItemAdapter : RecyclerView.Adapter<MediaItemViewHolder>() {
    var items: List<MediaItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemViewHolder {
        val view = parent.inflate(R.layout.view_media_item)
        return MediaItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MediaItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class MediaItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: MediaItem) {
        itemView.findViewById<ImageView>(R.id.mediaThumb).loadUrl(item.url)
        itemView.findViewById<TextView>(R.id.mediaTitle).text = item.title
        itemView.setOnClickListener { toast(item.title) }
    }
}