package practice.omdb.junseokseo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import practice.omdb.junseokseo.api.OMDbApi
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideSearchApi(retrofit: Retrofit): OMDbApi =
        retrofit.create(OMDbApi::class.java)
}