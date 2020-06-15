package com.learnwright.kotlindi.di.application

import com.learnwright.kotlindi.di.presentation.PresentationComponent
import com.learnwright.kotlindi.di.presentation.PresentationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkingModule::class])
interface ApplicationComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}