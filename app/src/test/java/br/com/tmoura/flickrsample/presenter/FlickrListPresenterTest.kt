package br.com.tmoura.flickrsample.presenter

import br.com.tmoura.domain.interactor.SearchItemsInteractor
import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.flickrsample.contract.FlickrList
import br.com.tmoura.flickrsample.model.FlickrImageItems
import br.com.tmoura.flickrsample.utils.RxSchedulers
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import junit.framework.Assert.assertTrue
import org.junit.Test

class FlickrListPresenterTest {

    private val onSearchSubject : PublishSubject<String> = PublishSubject.create()
    private val onScrolledToEndSubject : PublishSubject<FlickrImageItems> = PublishSubject.create()
    private val schedulers : RxSchedulers = mock {
        on { main() } doReturn Schedulers.single()
        on { background() } doReturn Schedulers.single()
    }
    private val viewMock : FlickrList.View = mock {
        on { onSearch() } doReturn onSearchSubject.hide()
        on { onScrolledToEnd() } doReturn onScrolledToEndSubject.hide()
    }

    private val image1 = FlickrImage("abc")
    private val image2 = FlickrImage("cds")
    private val image3 = FlickrImage("yuf")
    private val image4 = FlickrImage("iui")
    private val interactorMock : SearchItemsInteractor = mock {
        on {
            invoke(any(), eq(0))
        } doReturn Single.just(listOf(image1, image2))

        on {
            invoke(any(), eq(2))
        } doReturn Single.just(listOf(image3, image4))

        on {
            invoke(eq("exception"), any())
        } doReturn Single.error(Throwable("request failed!"))
    }
    private val presenter = FlickrListPresenter(viewMock, interactorMock, schedulers)

    @Test
    fun `when user search for a term return a list of images`() {
        presenter.subscribe()
        val expectedItems = FlickrImageItems("term", listOf(image1, image2))
        onSearchSubject.onNext("term")
        verify(viewMock, timeout(50)).showItems(expectedItems)
    }

    @Test
    fun `when user scrolls to end of list return more images`() {
        presenter.subscribe()
        val currentItems = FlickrImageItems("term", listOf(image1, image2))
        val expectedItems = FlickrImageItems("term", listOf(image3, image4))
        onScrolledToEndSubject.onNext(currentItems)
        verify(viewMock, timeout(50)).showItems(expectedItems)
    }

    @Test
    fun `when screen unsubscribe dispose all disposables`() {
        presenter.subscribe()
        onScrolledToEndSubject.onNext(FlickrImageItems.empty())
        onSearchSubject.onNext("abc")
        presenter.unsubscribe()
        assertTrue(presenter.compositeDisposable.isDisposed)
        assertTrue(presenter.searchDisposable?.isDisposed ?: false)
    }

    @Test
    fun `when an exception is thrown show error message`() {
        presenter.subscribe()
        onScrolledToEndSubject.onNext(FlickrImageItems("exception", emptyList()))
        verify(viewMock, timeout(50)).showError("request failed!")
    }
}
