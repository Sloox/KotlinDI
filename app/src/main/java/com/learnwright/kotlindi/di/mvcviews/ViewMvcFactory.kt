package com.learnwright.kotlindi.di.mvcviews

import android.view.LayoutInflater
import android.view.ViewGroup
import com.learnwright.kotlindi.common.image.ImageLoader
import com.learnwright.kotlindi.screens.questiondetails.QuestionDetailsViewMvc
import com.learnwright.kotlindi.screens.questiondetails.QuestionDetailsViewMvcImpl
import com.learnwright.kotlindi.screens.questionlist.QuestionsListViewMvc
import com.learnwright.kotlindi.screens.questionlist.QuestionsListViewMvcImpl
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val mLayoutInflater: LayoutInflater, private val mImageLoader: ImageLoader) {

    /**
     * Instantiate a new implementation of MVC view. The returned instance will be casted to MVC view
     * type inferred by java's automatic type inference.
     *
     * @param mvcViewClass the class of the required MVC view
     * @param container    this container will be used as MVC view's parent. See [LayoutInflater.inflate]
     * @param <T>          the type of the required MVC view
     * @return new instance of MVC view
    </T> */
    fun <T : ViewMvc> newInstance(mvcViewClass: Class<T>, container: ViewGroup?): T {
        val viewMvc: ViewMvc
        viewMvc = when (mvcViewClass) {
            QuestionsListViewMvc::class.java -> {
                QuestionsListViewMvcImpl(mLayoutInflater, container)
            }
            QuestionDetailsViewMvc::class.java -> {
                QuestionDetailsViewMvcImpl(mLayoutInflater, container, mImageLoader)
            }
            else -> {
                throw IllegalArgumentException("unsupported MVC view class $mvcViewClass")
            }
        }
        return viewMvc as T
    }

}