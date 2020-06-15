package com.learnwright.kotlindi.common.dialogs

import androidx.annotation.UiThread
import androidx.fragment.app.DialogFragment
import com.learnwright.kotlindi.application.KotlinDIApplication
import com.learnwright.kotlindi.di.application.ApplicationComponent
import com.learnwright.kotlindi.di.presentation.PresentationComponent
import com.learnwright.kotlindi.di.presentation.PresentationModule

abstract class BaseDialog : DialogFragment() {
    private var mIsInjectorUsed = false

    @get:UiThread
    protected val presentationComponent: PresentationComponent
        get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("there is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return applicationComponent.newPresentationComponent(PresentationModule(requireActivity()))
        }

    private val applicationComponent: ApplicationComponent get() = (activity!!.application as KotlinDIApplication).getApplicationComponent()
}