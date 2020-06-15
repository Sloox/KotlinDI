package com.learnwright.kotlindi.application

import android.app.Application
import com.learnwright.kotlindi.di.application.ApplicationComponent
import com.learnwright.kotlindi.di.application.ApplicationModule
import com.learnwright.kotlindi.di.application.DaggerApplicationComponent

class KotlinDIApplication : Application() {
    private lateinit var mApplicationComponent: ApplicationComponent
    internal fun getApplicationComponent() = mApplicationComponent


    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}