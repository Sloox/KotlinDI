package com.learnwright.kotlindi.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.learnwright.kotlindi.R
import com.learnwright.kotlindi.common.image.ImageLoader
import com.learnwright.kotlindi.di.mvcviews.BaseViewMvc
import com.learnwright.kotlindi.usecases.questions.QuestionDetails

class QuestionDetailsViewMvcImpl(inflater: LayoutInflater, container: ViewGroup?, imageLoader: ImageLoader) : BaseViewMvc<QuestionDetailsViewMvc.Listener>(), QuestionDetailsViewMvc {
    private val mImageLoader: ImageLoader = imageLoader
    private val mTxtQuestionBody: TextView
    private val mTxtUserDisplayName: TextView
    private val mImgUserAvatar: ImageView


    override fun bindQuestion(question: QuestionDetails) {
        val questionBody: String = question.body
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mTxtQuestionBody.text = Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)
        } else {
            mTxtQuestionBody.text = Html.fromHtml(questionBody)
        }
        mTxtUserDisplayName.text = question.userDisplayName
        mImageLoader.loadImage(question.userAvatarUrl, mImgUserAvatar)
    }

    init {
        rootView = (inflater.inflate(R.layout.layout_question_details, container, false))
        mTxtQuestionBody = findViewById(R.id.txt_question_body)
        mTxtUserDisplayName = findViewById(R.id.txt_user_display_name)
        mImgUserAvatar = findViewById(R.id.img_user_avatar)
    }
}