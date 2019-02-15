package br.com.tmoura.flickrsample.di

import br.com.tmoura.flickrsample.FlickrListActivity
import br.com.tmoura.flickrsample.di.activity.FlickrListActivityModule
import br.com.tmoura.flickrsample.di.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindings {

    @PerActivity
    @ContributesAndroidInjector(modules = [
        FlickrListActivityModule::class
    ])
    abstract fun contributeFlickrListActivityInjector(): FlickrListActivity

}
