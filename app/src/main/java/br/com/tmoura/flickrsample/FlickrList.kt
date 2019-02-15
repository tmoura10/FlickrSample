package br.com.tmoura.flickrsample

import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.flickrsample.di.scopes.PerActivity
import io.reactivex.Observable

interface FlickrList {

    interface View {
        fun showItems(items: List<FlickrImage>)
        fun onSearch(): Observable<String>
        fun showError(message: String?)
    }

    @PerActivity
    interface Presenter {
        fun subscribe()
    }
}
