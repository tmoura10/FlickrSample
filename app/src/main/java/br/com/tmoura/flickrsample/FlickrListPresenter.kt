package br.com.tmoura.flickrsample

import br.com.tmoura.domain.interactor.SearchItemsInteractor
import br.com.tmoura.network.FlickrDataSetImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FlickrListPresenter(
    val view: FlickrList.View,
    val searchItemsInteractor: SearchItemsInteractor = SearchItemsInteractor(FlickrDataSetImpl())
) : FlickrList.Presenter {

    var searchDisposable: Disposable? = null

    init {
        view.onSearch()
            .subscribeBy(onNext = this::searchItems)
    }

    private fun searchItems(term: String) {
        searchDisposable?.dispose()
        searchDisposable = searchItemsInteractor(term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = view::showItems,
                onError = this::handleError
            )
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
        view.showError(throwable.localizedMessage)
    }

}
