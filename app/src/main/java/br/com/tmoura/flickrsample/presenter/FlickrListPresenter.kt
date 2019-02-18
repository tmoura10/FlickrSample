package br.com.tmoura.flickrsample.presenter

import br.com.tmoura.domain.interactor.SearchItemsInteractor
import br.com.tmoura.flickrsample.contract.FlickrList
import br.com.tmoura.flickrsample.di.scopes.PerActivity
import br.com.tmoura.flickrsample.model.FlickrImageItems
import br.com.tmoura.flickrsample.utils.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@PerActivity
class FlickrListPresenter @Inject constructor(
    private val view: FlickrList.View,
    private val searchItemsInteractor: SearchItemsInteractor,
    private val schedulers: RxSchedulers
) : FlickrList.Presenter {

    internal var searchDisposable: Disposable? = null

    internal var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val emptyText = ""

    override fun subscribe() {
        dispose()
        compositeDisposable = CompositeDisposable()
        setup()
    }

    private fun dispose() {
        compositeDisposable.dispose()
        searchDisposable?.dispose()
    }

    private fun Disposable.registerDisposable() {
        compositeDisposable.add(this)
    }

    private fun setup() {
        view.onSearch()
            .subscribeBy(onNext = { term -> searchItems(term) })
            .registerDisposable()

        view.onScrolledToEnd()
            .subscribeBy(onNext = { item -> searchItems(item.term, item.items.size) })
            .registerDisposable()
    }

    private fun searchItems(term: String, itemCount: Int = 0) {
        when (term) {
            emptyText -> view.showItems(FlickrImageItems.empty())
            else -> {
                searchDisposable?.dispose()
                searchDisposable = searchItemsInteractor(term, itemCount)
                    .subscribeOn(schedulers.background())
                    .observeOn(schedulers.main())
                    .map { items -> FlickrImageItems(term, items) }
                    .subscribeBy(
                        onSuccess = view::showItems,
                        onError = this::handleError
                    )
            }
        }
    }

    override fun unsubscribe() {
        dispose()
    }

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
        view.showError(throwable.message)
    }

}
