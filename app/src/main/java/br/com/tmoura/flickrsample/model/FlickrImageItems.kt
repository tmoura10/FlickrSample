package br.com.tmoura.flickrsample.model

import br.com.tmoura.domain.model.FlickrImage

data class FlickrImageItems(
    val term: String,
    val items: List<FlickrImage>
) {
    companion object {
        fun empty() = FlickrImageItems("", mutableListOf())
    }
}
