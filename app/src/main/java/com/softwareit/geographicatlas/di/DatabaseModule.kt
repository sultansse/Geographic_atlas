package com.softwareit.geographicatlas.di

import android.content.Context
import androidx.room.Room
import com.softwareit.geographicatlas.data.local.CountryDatabase
import com.softwareit.geographicatlas.data.local.LocalDataSource
import com.softwareit.geographicatlas.data.local.dao.CountryDao
import com.softwareit.geographicatlas.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        CountryDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: CountryDatabase) = database.countryDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(countryDao: CountryDao): LocalDataSource {
        return LocalDataSource(countryDao)
    }
}