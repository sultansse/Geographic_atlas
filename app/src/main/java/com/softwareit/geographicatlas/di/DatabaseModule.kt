package com.softwareit.geographicatlas.di

//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//    @Singleton
//    @Provides
//    fun provideDatabase(
//        @ApplicationContext context: Context
//    ) = Room.databaseBuilder(
//        context,
//        CountryDatabase::class.java,
//        Constants.DATABASE_NAME
//    ).build()
//
//    @Singleton
//    @Provides
//    fun provideDao(database: CountryDatabase) = database.countryDao()
//
//    @Singleton
//    @Provides
//    fun provideLocalDataSource(countryDao: CountryDao): LocalDataSource {
//        return LocalDataSource(countryDao)
//    }
//}