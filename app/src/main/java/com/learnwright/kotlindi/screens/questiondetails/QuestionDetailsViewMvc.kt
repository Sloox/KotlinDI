package com.learnwright.kotlindi.screens.questiondetails

import com.learnwright.kotlindi.di.mvcviews.ObservableViewMvc
import com.learnwright.kotlindi.usecases.questions.QuestionDetails

interface QuestionDetailsViewMvc : ObservableViewMvc<QuestionDetailsViewMvc.Listener> {
    interface Listener { // currently no user actions
    }

    fun bindQuestion(question: QuestionDetails)
}