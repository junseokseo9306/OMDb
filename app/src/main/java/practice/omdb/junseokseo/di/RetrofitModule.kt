package practice.omdb.junseokseo.di

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import practice.omdb.junseokseo.Constants
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideSearchRetrofit(
        provideConverterFactory: Converter.Factory,
        provideOkHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.OMDB_BASE_URL)
        .client(provideOkHttpClient)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .addConverterFactory(provideConverterFactory)
        .build()
}