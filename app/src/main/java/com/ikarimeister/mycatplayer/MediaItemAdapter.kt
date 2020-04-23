package com.ikarimeister.mycatplayer

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_media_item.view.*

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
        itemView.mediaThumb.loadUrl(item.url)
        itemView.mediaTitle.text = item.title
        itemView.setOnClickListener { toast(item.title) }
        itemView.mediaVideoIndicator.visibility = when (item.type) {
            MediaType.VIDEO -> View.VISIBLE
            MediaType.PHOTO -> View.GONE
        }
    }
}