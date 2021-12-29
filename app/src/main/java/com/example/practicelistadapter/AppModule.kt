package com.example.practicelistadapter

import com.example.practicelistadapter.data.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()


    @Provides
    fun provideBaseUrl() = Constans.BASE_URL_WANAMA

    @Singleton
    @Provides
    fun getRetrofitInstance(base_url: String): ApiService = Retrofit.Builder()
        .baseUrl(base_url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}