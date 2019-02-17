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
    override fun search(term: String, itemsPerPage: Int, page: Int): Single<List<FlickrImage>> {
        return service.search(
            perPage = itemsPerPage,
            text = term,
            page = page
        ).map { item -> item.toDomain() }
    }
}
