package br.com.tmoura.network.service

import br.com.tmoura.network.model.FlickrResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    companion object {
        const val apiKey: String = "3e7cc266ae2b0e0d78e279ce8e361736"
        const val baseUrl: String = "https://api.flickr.com"
    }

    @GET("services/rest")
    fun search(
        @Query("text") text: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Companion.apiKey,
        @Query("method") method: String = "flickr.photos.search",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): Single<FlickrResponse>

}
