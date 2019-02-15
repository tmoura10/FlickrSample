package br.com.tmoura.network

import br.com.tmoura.domain.dataset.FlickrDataSet
import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.network.mapper.toDomain
import io.reactivex.Single

class FlickrDataSetImpl : FlickrDataSet {
    override fun search(term: String): Single<List<FlickrImage>> {
        val service = FlickrServiceImpl().build()

        return service.search(
            perPage = 50,
            text = term,
            page = 1
        ).map { item ->
            item.photos.photo.map { photo -> photo.toDomain() }
        }
    }
}
