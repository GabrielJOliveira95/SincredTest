package com.example.sincredtest.application

import android.app.Application
import com.example.sincredtest.data.di.SincredModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SincredApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        startKoin {
            androidContext(this@SincredApplication)
            printLogger()
            modules(SincredModule.instance)
        }
    }
}