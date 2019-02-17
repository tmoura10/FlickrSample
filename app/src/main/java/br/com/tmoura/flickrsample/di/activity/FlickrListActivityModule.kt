package br.com.tmoura.flickrsample.di.activity

import br.com.tmoura.flickrsample.contract.FlickrList
import br.com.tmoura.flickrsample.activity.FlickrListActivity
import br.com.tmoura.flickrsample.presenter.FlickrListPresenter
import dagger.Binds
import dagger.Module

@Module
interface FlickrListActivityModule {

    @Binds
    fun bindPresenter(impl: FlickrListPresenter): FlickrList.Presenter

    @Binds
    fun bindView(impl: FlickrListActivity): FlickrList.View

}
