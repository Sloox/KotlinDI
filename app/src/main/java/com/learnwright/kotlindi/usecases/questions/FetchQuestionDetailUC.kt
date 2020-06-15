package com.learnwright.kotlindi.usecases.questions

import androidx.annotation.Nullable
import com.learnwright.kotlindi.common.BaseObservable
import com.learnwright.kotlindi.di.networking.QuestionSchema
import com.learnwright.kotlindi.di.networking.SingleQuestionResponseSchema
import com.learnwright.kotlindi.di.networking.StackOverflowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchQuestionDetailUC(private val stackOverflowApi: StackOverflowApi) : BaseObservable<FetchQuestionDetailUC.Listener>() {
    interface Listener {
        fun onFetchOfQuestionDetailsSucceeded(question: QuestionDetails)
        fun onFetchOfQuestionDetailsFailed()
    }

    @Nullable
    var mCall: Call<SingleQuestionResponseSchema>? = null


    fun fetchQuestionDetailsAndNotify(questionID: String) {
        cancelCurrentFetchIfActive()
        mCall = stackOverflowApi.questionDetails(questionID)
        mCall!!.enqueue(object : Callback<SingleQuestionResponseSchema> {
            override fun onResponse(call: Call<SingleQuestionResponseSchema>, response: Response<SingleQuestionResponseSchema>) {
                if (response.isSuccessful) {
                    notifySucceeded(questionDetailsFromQuestionSchema(response.body()!!.question))
                } else {
                    notifyFailed()
                }
            }

            override fun onFailure(call: Call<SingleQuestionResponseSchema>, t: Throwable) {
                notifyFailed()
            }
        })
    }

    private fun questionDetailsFromQuestionSchema(question: QuestionSchema): QuestionDetails {
        return QuestionDetails(question.id, question.title, question.body, question.owner.userDisplayName, question.owner.userAvatarUrl)
    }

    private fun notifySucceeded(question: QuestionDetails) {
        for (listener in getListeners()) {
            listener.onFetchOfQuestionDetailsSucceeded(question)
        }
    }

    private fun notifyFailed() {
        for (listener in getListeners()) {
            listener.onFetchOfQuestionDetailsFailed()
        }
    }

    private fun cancelCurrentFetchIfActive() {
        if (mCall != null && !mCall!!.isCanceled && !mCall!!.isExecuted) {
            mCall?.cancel()
        }
    }
}