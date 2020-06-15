package com.learnwright.kotlindi.usecases.questions

import androidx.annotation.Nullable
import com.learnwright.kotlindi.common.BaseObservable
import com.learnwright.kotlindi.di.networking.QuestionSchema
import com.learnwright.kotlindi.di.networking.QuestionsListResponseSchema
import com.learnwright.kotlindi.di.networking.StackOverflowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FetchQuestionListUC(private val stackOverflowApi: StackOverflowApi) : BaseObservable<FetchQuestionListUC.Listener>() {
    interface Listener {
        fun onFetchOfQuestionsSucceeded(question: List<Question>)
        fun onFetchOfQuestionsFailed()
    }

    @Nullable
    var mCall: Call<QuestionsListResponseSchema>? = null


    fun fetchQuestionsAndNotify(numQuestions: Int) {
        cancelCurrentFetchIfActive()
        mCall = stackOverflowApi.lastActiveQuestions(numQuestions)
        mCall!!.enqueue(object : Callback<QuestionsListResponseSchema> {
            override fun onResponse(call: Call<QuestionsListResponseSchema>, response: Response<QuestionsListResponseSchema>) {
                if (response.isSuccessful) {
                    questionsFromQuestionsSchemas(response.body()!!.questions)?.let { notifySucceeded(it) }
                } else {
                    notifyFailed()
                }
            }

            override fun onFailure(call: Call<QuestionsListResponseSchema>, t: Throwable) {
                notifyFailed()
            }
        })
    }

    private fun questionsFromQuestionsSchemas(questionSchemas: List<QuestionSchema>): List<Question>? {
        val questions: MutableList<Question> = ArrayList(questionSchemas.size)
        for (questionSchema in questionSchemas) {
            questions.add(Question(questionSchema.id, questionSchema.title))
        }
        return questions
    }

    private fun notifySucceeded(questions: List<Question>) {
        val unmodifiableQuestions = Collections.unmodifiableList(questions)
        for (listener in getListeners()) {
            listener.onFetchOfQuestionsSucceeded(unmodifiableQuestions)
        }
    }

    private fun notifyFailed() {
        for (listener in getListeners()) {
            listener.onFetchOfQuestionsFailed()
        }
    }

    private fun cancelCurrentFetchIfActive() {
        if (mCall != null && !mCall!!.isCanceled && !mCall!!.isExecuted) {
            mCall?.cancel()
        }
    }
}