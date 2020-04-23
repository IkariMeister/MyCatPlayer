package com.ikarimeister.mycatplayer

fun getItems(count: Int = 10): List<MediaItem> =
        (1..count).map {
            MediaItem("Title $it", "https://placekitten.com/200/200?image=$it", when (it % 2) {
                0 -> MediaType.VIDEO
                else -> MediaType.PHOTO
            })
        }