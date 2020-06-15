package com.learnwright.kotlindi.di.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.learnwright.kotlindi.common.dialogs.DialogsManager
import com.learnwright.kotlindi.common.image.ImageLoader
import com.learnwright.kotlindi.di.networking.StackOverflowApi
import com.learnwright.kotlindi.usecases.questions.FetchQuestionDetailUC
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val mActivity: FragmentActivity) {
    @Provides
    fun getFragmentManager(): FragmentManager = mActivity.supportFragmentManager

    @Provides
    fun getLayoutInflater(): LayoutInflater = LayoutInflater.from(mActivity)

    @Provides
    fun getActivity(): Activity = mActivity

    @Provides
    fun context(activity: Activity): Context = activity

    @Provides
    fun getFetchQuestionDetailsUseCase(stackoverflowApi: StackOverflowApi) = FetchQuestionDetailUC(stackoverflowApi)

    @Provides
    fun getDialogsManager(fragmentManager: FragmentManager) = DialogsManager(fragmentManager)

    @Provides
    fun getImageLoader(activity: Activity) = ImageLoader(activity)
}