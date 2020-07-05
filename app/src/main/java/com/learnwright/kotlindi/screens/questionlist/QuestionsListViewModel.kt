package com.learnwright.kotlindi.screens.questionlist

import androidx.lifecycle.ViewModel
import com.learnwright.kotlindi.usecases.questions.Question
import java.util.*

class QuestionsListViewModel : ViewModel() {
    private var mQuestions: List<Question> = ArrayList<Question>()
    var questions: List<Question>
        get() = mQuestions
        set(questions) {
            mQuestions = questions
        }
}