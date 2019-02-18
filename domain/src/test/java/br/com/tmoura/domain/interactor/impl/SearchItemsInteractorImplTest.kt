package br.com.tmoura.domain.interactor.impl

import br.com.tmoura.domain.dataset.FlickrDataSet
import br.com.tmoura.domain.model.FlickrImage
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*

class SearchItemsInteractorImplTest {

    private val image1 = FlickrImage("abc")
    private val image2 = FlickrImage("cbl")
    private val dataSetMock: FlickrDataSet = mock {
        on { search(any(), any(), any()) } doReturn Single.just(
            listOf(image1, image2)
        )
    }
    private val interactorMock = SearchItemsInteractorImpl(dataSetMock)

    @Test
    fun `when a term is searched return a list of images`() {
        val result = interactorMock("abc").test()
        result.assertValue(listOf(image1, image2))
    }

    @Test
    fun `when a term is searched search for the correct number of items`() {
        val expectedNumberOfItemsPerPage = 50
        interactorMock("abc", 75).test()
        verify(dataSetMock).search(any(), eq(expectedNumberOfItemsPerPage), any())
    }

    @Test
    fun `when a term is given search for the correct term`() {
        val expectedTerm = "abcd"
        interactorMock(expectedTerm).test()
        verify(dataSetMock).search(eq(expectedTerm), any(), any())
    }

    @Test
    fun `when a number of loaded items is given search for the correct page`() {
        val loadedItems = 70
        val expectedPageToSearch = 2
        interactorMock("abc", loadedItems).test()
        verify(dataSetMock).search(any(), any(), eq(expectedPageToSearch))
    }

    @Test
    fun `when there is no loaded items search for the first page`() {
        interactorMock("abc").test()
        val expectedPageToSearch = 1
        verify(dataSetMock).search(any(), any(), eq(expectedPageToSearch))
    }
}
