package br.com.tmoura.domain.interactor

import br.com.tmoura.domain.dataset.FlickrDataSet
import br.com.tmoura.domain.model.FlickrImage
import io.reactivex.Single

class SearchItemsInteractor(val dataSet: FlickrDataSet) {
    operator fun invoke(term: String): Single<List<FlickrImage>> {
        return dataSet.search(term)
    }
}
