package br.com.tmoura.network.dataset

import br.com.tmoura.network.mapper.toDomain
import br.com.tmoura.network.model.FlickrResponse
import br.com.tmoura.network.service.FlickrService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

class FlickrDataSetImplTest {

    private val photo1 = FlickrResponse.Photo("1", 1, "abc", "abcd")
    private val photo2 = FlickrResponse.Photo("2", 2, "abcd", "abcde")
    private val images = buildFlickrImages(photo1, photo2)
    private val serviceMock: FlickrService = mock {
        on { search(any(), any(), any(), any(), any(), any(), any()) } doReturn Single.just(buildFlickrResponse(photo1, photo2))
    }

    @Test
    fun `when a valid response is given return a list of images`() {
        val dataSet = FlickrDataSetImpl(serviceMock)
        val test = dataSet.search("abc", 10, 1).test()
        test.assertValue(images)
    }

    private fun buildFlickrResponse(vararg photos: FlickrResponse.Photo) =
        FlickrResponse(
            FlickrResponse.Photos(
                listOf(*photos)
            )
        )

    private fun buildFlickrImages(vararg photos: FlickrResponse.Photo) =
        photos.map { it.toDomain() }
}
