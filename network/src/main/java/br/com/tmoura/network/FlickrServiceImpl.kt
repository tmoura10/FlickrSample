package br.com.tmoura.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//TODO remove this class
class FlickrServiceImpl {
    fun build(): FlickrService = Retrofit.Builder()
        .baseUrl("https://api.flickr.com")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(FlickrService::class.java)
}
