package com.learnwright.kotlindi.di.presentation


import com.learnwright.kotlindi.screens.questiondetails.QuestionDetailsActivity
import com.learnwright.kotlindi.screens.questionlist.QuestionsListActivity
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {
    fun inject(questionsListActivity: QuestionsListActivity)
    fun inject(questionDetailsActivity: QuestionDetailsActivity)
}