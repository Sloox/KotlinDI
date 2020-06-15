package com.learnwright.kotlindi.di.networking

import com.google.gson.annotations.SerializedName

class QuestionSchema(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("question_id") val id: String,
    @field:SerializedName("body") val body: String,
    @field:SerializedName("owner") val owner: UserSchema
)