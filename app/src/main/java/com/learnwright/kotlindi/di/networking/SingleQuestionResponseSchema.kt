package com.learnwright.kotlindi.di.networking

import com.google.gson.annotations.SerializedName

class SingleQuestionResponseSchema(question: QuestionSchema) {
    @SerializedName("items")
    private val mQuestions: List<QuestionSchema> = listOf(question)
    val question: QuestionSchema
        get() = mQuestions[0]

}