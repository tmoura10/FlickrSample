package br.com.tmoura.flickrsample.di

import br.com.tmoura.domain.di.DomainModule
import br.com.tmoura.flickrsample.App
import br.com.tmoura.network.di.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindings::class,
        NetworkModule::class,
        DomainModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
