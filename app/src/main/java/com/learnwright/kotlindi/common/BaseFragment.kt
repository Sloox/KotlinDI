package com.learnwright.kotlindi.common

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.learnwright.kotlindi.application.KotlinDIApplication
import com.learnwright.kotlindi.di.application.ApplicationComponent
import com.learnwright.kotlindi.di.presentation.PresentationComponent
import com.learnwright.kotlindi.di.presentation.PresentationModule

abstract class BaseFragment : Fragment() {
    private var mIsInjectorUsed = false

    @get:UiThread
    protected val presentationComponent: PresentationComponent
        protected get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("there is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return applicationComponent.newPresentationComponent(PresentationModule(requireActivity()))
        }

    private val applicationComponent: ApplicationComponent
        private get() = (requireActivity().application as KotlinDIApplication).getApplicationComponent()
}
