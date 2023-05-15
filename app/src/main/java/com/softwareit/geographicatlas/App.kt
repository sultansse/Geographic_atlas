package com.softwareit.geographicatlas

import android.app.Application
import com.softwareit.geographicatlas.utils.Constants.Companion.DATABASE_NAME
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
//        clean()
    }

    private fun clean() {
        this.deleteDatabase(DATABASE_NAME)
    }
}