package com.esmaeel.challenge.di

import com.esmaeel.challenge.data.remote.RemoteDataSource
import com.esmaeel.challenge.data.repositories.MoviesRepository
import com.esmaeel.challenge.domain.repositories.IMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(
        remoteDataSource: RemoteDataSource,
        contextProvider: ContextProvider,
        resourcesHandler: ResourcesHandler
    ): IMoviesRepository {
        return MoviesRepository(
            remoteDataSource = remoteDataSource,
            contextProvider = contextProvider,
            resourcesHandler = resourcesHandler
        )
    }

}
