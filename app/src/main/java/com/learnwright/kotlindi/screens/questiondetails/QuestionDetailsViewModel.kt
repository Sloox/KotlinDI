package com.learnwright.kotlindi.screens.questiondetails

import androidx.lifecycle.ViewModel
import com.learnwright.kotlindi.usecases.questions.FetchQuestionDetailUC
import com.learnwright.kotlindi.usecases.questions.QuestionDetails
import java.util.*

class QuestionDetailsViewModel(private val mFetchQuestionDetailsUseCase: FetchQuestionDetailUC) : ViewModel(), FetchQuestionDetailUC.Listener {
    interface Listener {
        fun onQuestionDetailsFetched(questionDetails: QuestionDetails)
        fun onQuestionDetailsFetchFailed()
    }

    private val mListeners: MutableSet<Listener> = HashSet()
    private var mQuestionDetails: QuestionDetails? = null
    override fun onCleared() {
        super.onCleared()
        mFetchQuestionDetailsUseCase.unregisterListener(this)
    }

    fun fetchQuestionDetailsAndNotify(questionId: String) {
        if (mQuestionDetails == null || mQuestionDetails!!.id != questionId) {
            mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId)
        } else {
            notifySuccess(mQuestionDetails!!)
        }
    }

    override fun onFetchOfQuestionDetailsSucceeded(question: QuestionDetails) {
        mQuestionDetails = question
        notifySuccess(question)
    }

    override fun onFetchOfQuestionDetailsFailed() {
        notifyFailure()
    }

    private fun notifyFailure() {
        for (listener in mListeners) {
            listener.onQuestionDetailsFetchFailed()
        }
    }

    private fun notifySuccess(questionDetails: QuestionDetails) {
        for (listener in mListeners) {
            listener.onQuestionDetailsFetched(questionDetails)
        }
    }

    fun registerListener(listener: Listener) {
        mListeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        mListeners.remove(listener)
    }

    init {
        mFetchQuestionDetailsUseCase.registerListener(this)
    }
}