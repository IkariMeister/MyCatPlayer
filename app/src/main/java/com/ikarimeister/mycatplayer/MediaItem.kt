package com.ikarimeister.mycatplayer

data class MediaItem(val title: String, val url: String, val type: MediaType)

enum class MediaType { PHOTO, VIDEO }