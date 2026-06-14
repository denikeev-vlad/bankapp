package ru.vladkempo.bankapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vladkempo.bankapp.data.remote.repository.OperationRepositoryImpl
import ru.vladkempo.bankapp.domain.OperationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindOperationRepository(
        impl: OperationRepositoryImpl
    ): OperationRepository
}