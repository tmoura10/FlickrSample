package br.com.tmoura.flickrsample

import br.com.tmoura.domain.interactor.SearchItemsInteractor
import br.com.tmoura.flickrsample.di.scopes.PerActivity
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
