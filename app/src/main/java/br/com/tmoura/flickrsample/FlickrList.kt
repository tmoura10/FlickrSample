package br.com.tmoura.flickrsample

import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.flickrsample.di.scopes.PerActivity
import br.com.tmoura.flickrsample.model.FlickrImageItems
import io.reactivex.Observable

interface FlickrList {

    interface View {
        fun showItems(items: FlickrImageItems)
        fun onSearch(): Observable<String>
        fun onScrolledToEnd(): Observable<FlickrImageItems>
        fun showError(message: String?)
    }

    @PerActivity
    interface Presenter {
        fun subscribe()
    }
}
