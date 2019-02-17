package br.com.tmoura.domain.interactor.impl

import br.com.tmoura.domain.dataset.FlickrDataSet
import br.com.tmoura.domain.interactor.SearchItemsInteractor
import br.com.tmoura.domain.model.FlickrImage
import io.reactivex.Single
import javax.inject.Inject

class SearchItemsInteractorImpl @Inject constructor(
    private val dataSet: FlickrDataSet
) : SearchItemsInteractor {

    private val itemsPerPage = 50

    override operator fun invoke(term: String, itemsLoaded: Int): Single<List<FlickrImage>> {
        return Single
            .fromCallable { nextPage(itemsLoaded) }
            .flatMap { page ->
                dataSet.search(term, itemsPerPage, page)
            }
    }

    private fun nextPage(itemsLoaded: Int) = itemsLoaded.div(itemsPerPage) + 1
}
