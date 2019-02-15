package br.com.tmoura.network.di

import br.com.tmoura.domain.dataset.FlickrDataSet
import br.com.tmoura.network.dataset.FlickrDataSetImpl
import br.com.tmoura.network.service.FlickrService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule.Bindings::class])
object NetworkModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideCallAdapter(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    @JvmStatic
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @JvmStatic
    @Singleton
    fun provideService(
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ) = Retrofit.Builder()
        .baseUrl(FlickrService.baseUrl)
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .build()
        .create(FlickrService::class.java)

    @Module
    interface Bindings {
        @Binds
        fun bindsDataSet(impl: FlickrDataSetImpl): FlickrDataSet
    }

}
