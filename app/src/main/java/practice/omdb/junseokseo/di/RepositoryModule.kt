package practice.omdb.junseokseo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import practice.omdb.junseokseo.repository.SearchRepository
import practice.omdb.junseokseo.repository.SearchRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ) : SearchRepository
}