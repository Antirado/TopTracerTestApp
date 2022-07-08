package com.example.toptracertest

import androidx.multidex.MultiDexApplication
import com.example.toptracertest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TopTracerTestApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@TopTracerTestApplication)
            modules(listOf(appModule))
        }
    }
}