package br.com.tmoura.network.model

data class FlickrItem(
    val photos: Photos
)

data class Photos(
    val photo: List<Photo>
)

data class Photo(val id: String,
                 val farm: Long,
                 val secret: String,
                 val server: String)
