package br.com.tmoura.domain.di

import br.com.tmoura.domain.interactor.SearchItemsInteractor
import br.com.tmoura.domain.interactor.impl.SearchItemsInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindSearchItems(impl: SearchItemsInteractorImpl): SearchItemsInteractor
}
