package br.com.tmoura.flickrsample

import br.com.tmoura.domain.interactor.SearchItemsInteractor
import br.com.tmoura.flickrsample.di.scopes.PerActivity
import br.com.tmoura.flickrsample.model.FlickrImageItems
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerActivity
class FlickrListPresenter @Inject constructor(
    private val view: FlickrList.View,
    private val searchItemsInteractor: SearchItemsInteractor
) : FlickrList.Presenter {

    private var searchDisposable: Disposable? = null

    override fun subscribe() {
        setup()
    }

    private fun setup() {
        view.onSearch()
            .subscribeBy(onNext = { term -> searchItems(term) })

        view.onScrolledToEnd()
            .subscribeBy(onNext = { item -> searchItems(item.term, item.items.size) })
    }

    private fun searchItems(term: String, itemCount: Int = 0) {
        searchDisposable?.dispose()
        searchDisposable = searchItemsInteractor(term, itemCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { items -> FlickrImageItems(term, items) }
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
