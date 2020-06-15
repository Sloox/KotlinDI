package com.learnwright.kotlindi.di.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackOverflowApi {
    @GET("/questions?order=desc&sort=activity&site=stackoverflow&filter=withbody")
    fun lastActiveQuestions(@Query("pagesize") pageSize: Int): Call<QuestionsListResponseSchema>

    @GET("/questions/{questionId}?site=stackoverflow&filter=withbody")
    fun questionDetails(@Path("questionId") questionId: String): Call<SingleQuestionResponseSchema>
}