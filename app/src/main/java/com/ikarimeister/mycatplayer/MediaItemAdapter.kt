package com.ikarimeister.mycatplayer

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikarimeister.mycatplayer.databinding.ViewMediaItemBinding

class MediaItemAdapter(
    elements: List<MediaItem>,
    var listener: (MediaItem) -> Unit
) : RecyclerView.Adapter<MediaItemViewHolder>() {
    var items: List<MediaItem> = elements
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
        holder.bind(items[position], listener)
    }
}

class MediaItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val itemBinding: ViewMediaItemBinding = ViewMediaItemBinding.bind(view)

    fun bind(item: MediaItem, listener: (MediaItem) -> Unit) = with(itemBinding) {
        mediaThumb.loadUrl(item.url)
        mediaTitle.text = item.title
        mediaVideoIndicator.visibility = when (item.type) {
            MediaType.VIDEO -> View.VISIBLE
            MediaType.PHOTO -> View.GONE
        }
        root.setOnClickListener { listener(item) }
    }
}