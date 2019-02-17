package br.com.tmoura.domain.dataset

import br.com.tmoura.domain.model.FlickrImage
import io.reactivex.Single

interface FlickrDataSet {
    fun search(term: String, itemsPerPage: Int, page: Int): Single<List<FlickrImage>>
}
