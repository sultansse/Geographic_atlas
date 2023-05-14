package com.softwareit.geographicatlas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.softwareit.geographicatlas.data.local.dao.CountryDao
import com.softwareit.geographicatlas.data.local.entities.CountryEntity

/*@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        private var instance: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, CountryDatabase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as CountryDatabase
        }
    }
}*/

@Database(entities = [CountryEntity::class], version = 1)
@TypeConverters(CountryTypeConverter::class)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao
}
