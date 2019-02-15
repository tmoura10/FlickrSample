package br.com.tmoura.network

import br.com.tmoura.network.model.FlickrItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    companion object {
        val apiKey: String = "3e7cc266ae2b0e0d78e279ce8e361736"
    }

    @GET("services/rest?method=flickr.photos.search&format=json&nojsoncallback=1")
    fun search(
        @Query("text") text: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = FlickrService.apiKey,
        @Query("method") method: String = "flickr.photos.search",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): Single<FlickrItem>

}
