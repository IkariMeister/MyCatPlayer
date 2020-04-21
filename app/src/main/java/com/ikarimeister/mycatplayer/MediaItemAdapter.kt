package com.ikarimeister.mycatplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_media_item, parent, false)
        return MediaItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MediaItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class MediaItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: MediaItem) {
        Glide.with(itemView.context)
                .load(item.url)
                .into(itemView.findViewById(R.id.mediaThumb))
        itemView.findViewById<TextView>(R.id.mediaTitle).text = item.title
        itemView.setOnClickListener { toast(item.title) }
    }
}