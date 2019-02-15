package br.com.tmoura.domain.interactor.impl

import br.com.tmoura.domain.dataset.FlickrDataSet
import br.com.tmoura.domain.interactor.SearchItemsInteractor
import br.com.tmoura.domain.model.FlickrImage
import io.reactivex.Single
import javax.inject.Inject

class SearchItemsInteractorImpl @Inject constructor(
    val dataSet: FlickrDataSet
): SearchItemsInteractor {
    override operator fun invoke(term: String): Single<List<FlickrImage>> {
        return dataSet.search(term)
    }
}
