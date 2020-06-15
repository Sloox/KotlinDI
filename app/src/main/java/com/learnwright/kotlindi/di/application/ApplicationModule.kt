package com.learnwright.kotlindi.di.application

import android.app.Application
import com.learnwright.kotlindi.di.networking.StackOverflowApi
import com.learnwright.kotlindi.usecases.questions.FetchQuestionListUC
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val mApplication: Application) {
    @Provides
    fun getFetchQuestionsListUseCase(stackOverflowAPI: StackOverflowApi) = FetchQuestionListUC(stackOverflowAPI)
}