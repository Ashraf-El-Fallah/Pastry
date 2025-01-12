package com.example.pastry.di.repository

import com.example.pastry.data.remote.repository.PastryRepository
import com.example.pastry.data.remote.repository.PastryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class PastryRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindPastryRepository(
        pastryRepository: PastryRepositoryImpl,
    ): PastryRepository
}