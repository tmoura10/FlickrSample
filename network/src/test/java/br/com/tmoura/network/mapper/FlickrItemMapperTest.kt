package br.com.tmoura.network.mapper

import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.network.model.FlickrResponse
import junit.framework.Assert.assertEquals
import org.junit.Test

class FlickrItemMapperTest {

    @Test
    fun `when a server model is given return a domain model with converted url`() {
        val expected = FlickrImage("http://farm3.static.flickr.com/edf/abc_cba.jpg")
        val given = FlickrResponse.Photo("abc", 3, "cba", "edf")
        val result = given.toDomain()
        assertEquals(expected, result)
    }

}
