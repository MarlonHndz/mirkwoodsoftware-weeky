package com.mirkwoodsoftware.weeky

import android.app.Application
import com.mirkwoodsoftware.data.di.dataModule
import com.mirkwoodsoftware.domain.di.domainModule
import com.mirkwoodsoftware.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(domainModule, dataModule, presentationModule)
        }
    }
}
