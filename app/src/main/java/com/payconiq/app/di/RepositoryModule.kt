package com.payconiq.app.di

import com.payconiq.app.data.repository.DataRepository
import com.payconiq.app.data.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDataRepositoryModule(repository: DataRepositoryImpl) : DataRepository
}