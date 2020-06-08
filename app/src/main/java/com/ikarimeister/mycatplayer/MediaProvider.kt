package com.ikarimeister.mycatplayer

object MediaProvider {
    const val timeSleep: Long = 2000

    suspend fun getItems(count: Int = 10): List<MediaItem> {
        Thread.sleep(timeSleep)
        return (1..count).map {
            MediaItem(it, "Title $it", "https://placekitten.com/200/200?image=$it", when (it % 2) {
                0 -> MediaType.VIDEO
                else -> MediaType.PHOTO
            })
        }
    }
}