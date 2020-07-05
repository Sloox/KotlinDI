package com.learnwright.kotlindi.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.learnwright.kotlindi.common.BaseActivity
import com.learnwright.kotlindi.common.dialogs.DialogsManager
import com.learnwright.kotlindi.common.dialogs.ServerErrorDialogFragment
import com.learnwright.kotlindi.common.viewmodel.ViewModelFactory
import com.learnwright.kotlindi.di.mvcviews.ViewMvcFactory
import com.learnwright.kotlindi.usecases.questions.QuestionDetails
import javax.inject.Inject

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsViewMvc.Listener, QuestionDetailsViewModel.Listener {
    @Inject
    lateinit var mDialogsManager: DialogsManager

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    private lateinit var mQuestionId: String
    private lateinit var mViewMvc: QuestionDetailsViewMvc
    private lateinit var mQuestionDetailsViewModel: QuestionDetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
        mViewMvc = mViewMvcFactory.newInstance(QuestionDetailsViewMvc::class.java, null)
        mQuestionDetailsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(QuestionDetailsViewModel::class.java)
        setContentView(mViewMvc.rootView)
        mQuestionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
        mQuestionDetailsViewModel.registerListener(this)
        mQuestionDetailsViewModel.fetchQuestionDetailsAndNotify(mQuestionId)
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unregisterListener(this)
        mQuestionDetailsViewModel.unregisterListener(this)
    }

    override fun onQuestionDetailsFetched(questionDetails: QuestionDetails) {
        mViewMvc.bindQuestion(questionDetails)
    }

    override fun onQuestionDetailsFetchFailed() {
        mDialogsManager.showDialogWithId(ServerErrorDialogFragment.newInstance(), "")
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }
}
