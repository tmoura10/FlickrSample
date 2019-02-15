package br.com.tmoura.network.mapper

import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.network.model.FlickrResponse

fun FlickrResponse.Photo.toDomain() = FlickrImage(
    url = "http://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
)

fun FlickrResponse.toDomain() = this.photos.photo.map { photo -> photo.toDomain() }
