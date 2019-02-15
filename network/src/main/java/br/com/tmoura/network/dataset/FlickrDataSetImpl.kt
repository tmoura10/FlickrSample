package br.com.tmoura.network.dataset

import br.com.tmoura.domain.dataset.FlickrDataSet
import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.network.mapper.toDomain
import br.com.tmoura.network.service.FlickrService
import io.reactivex.Single
import javax.inject.Inject

class FlickrDataSetImpl @Inject constructor(
    private val service: FlickrService
) : FlickrDataSet {
    override fun search(term: String): Single<List<FlickrImage>> {
        return service.search(
            perPage = 10,
            text = term,
            page = 1
        ).map { item -> item.toDomain() }
    }
}
