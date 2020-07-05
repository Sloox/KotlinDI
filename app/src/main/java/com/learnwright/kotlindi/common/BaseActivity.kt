package com.learnwright.kotlindi.common

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.learnwright.kotlindi.application.KotlinDIApplication
import com.learnwright.kotlindi.di.application.ApplicationComponent
import com.learnwright.kotlindi.di.presentation.PresentationComponent
import com.learnwright.kotlindi.di.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {
    private var mIsInjectorUsed = false

    @UiThread
    protected fun getPresentationComponent(): PresentationComponent {
        if (mIsInjectorUsed) {
            throw RuntimeException("there is no need to use injector more than once")
        }
        mIsInjectorUsed = true
        return getApplicationComponent().newPresentationComponent(PresentationModule(this))
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (application as KotlinDIApplication).getApplicationComponent()
    }
}