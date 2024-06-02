package practice.omdb.junseokseo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkConverterModule {

    @Provides
    fun provideKotlinSerializationFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }
}