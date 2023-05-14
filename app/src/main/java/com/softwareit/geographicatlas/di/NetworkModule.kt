package com.softwareit.geographicatlas.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.softwareit.geographicatlas.data.remote.CountriesService
import com.softwareit.geographicatlas.data.remote.RemoteDataSource
import com.softwareit.geographicatlas.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideCountriesService(retrofit: Retrofit): CountriesService {
        return retrofit.create(CountriesService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(characterService: CountriesService): RemoteDataSource {
        return RemoteDataSource(characterService)
    }
}