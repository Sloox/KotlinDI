package com.learnwright.kotlindi.screens.questionlist

import com.learnwright.kotlindi.di.mvcviews.ObservableViewMvc
import com.learnwright.kotlindi.usecases.questions.Question

interface QuestionsListViewMvc : ObservableViewMvc<QuestionsListViewMvc.Listener> {
    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun bindQuestions(questions: List<Question>)
}