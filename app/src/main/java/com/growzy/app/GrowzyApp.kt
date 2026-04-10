package com.growzy.app

import android.app.Application
import com.growzy.app.di.AppContainer

class GrowzyApp : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}