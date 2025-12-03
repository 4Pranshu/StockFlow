package com.example.stockflow

import android.app.Application
import com.example.stockflow.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StockFlowApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StockFlowApp)
            androidLogger()

            //DI
            modules(appModule)
        }
    }
}