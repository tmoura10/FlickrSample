package br.com.tmoura.network.mapper

import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.network.model.Photo

fun Photo.toDomain() = FlickrImage(
    url = "http://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
)
