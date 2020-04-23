package com.ikarimeister.mycatplayer

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikarimeister.mycatplayer.databinding.ViewMediaItemBinding

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

    private val itemBinding: ViewMediaItemBinding = ViewMediaItemBinding.bind(view)

    fun bind(item: MediaItem) = with(itemBinding) {
        mediaThumb.loadUrl(item.url)
        mediaTitle.text = item.title
        mediaVideoIndicator.visibility = when (item.type) {
            MediaType.VIDEO -> View.VISIBLE
            MediaType.PHOTO -> View.GONE
        }
        itemView.setOnClickListener { toast(item.title) }
    }
}