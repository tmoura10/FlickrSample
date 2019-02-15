package br.com.tmoura.domain.interactor

import br.com.tmoura.domain.model.FlickrImage
import io.reactivex.Single

interface SearchItemsInteractor {
    operator fun invoke(term: String): Single<List<FlickrImage>>
}
