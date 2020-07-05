package com.learnwright.kotlindi.screens.questionlist


import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.learnwright.kotlindi.common.BaseActivity
import com.learnwright.kotlindi.common.dialogs.DialogsManager
import com.learnwright.kotlindi.common.dialogs.ServerErrorDialogFragment
import com.learnwright.kotlindi.common.viewmodel.ViewModelFactory
import com.learnwright.kotlindi.di.mvcviews.ViewMvcFactory
import com.learnwright.kotlindi.screens.questiondetails.QuestionDetailsActivity
import com.learnwright.kotlindi.usecases.questions.FetchQuestionListUC
import com.learnwright.kotlindi.usecases.questions.Question
import javax.inject.Inject

class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener, FetchQuestionListUC.Listener {
    @Inject
    lateinit var mFetchQuestionsListUseCase: FetchQuestionListUC

    @Inject
    lateinit var mDialogsManager: DialogsManager

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mViewModelFactory: @JvmSuppressWildcards ViewModelFactory


    private lateinit var mViewMvc: QuestionsListViewMvc
    private lateinit var mQuestionsListViewModel: QuestionsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
        mViewMvc = mViewMvcFactory.newInstance(QuestionsListViewMvc::class.java, null)
        mQuestionsListViewModel = ViewModelProviders.of(this, mViewModelFactory).get(QuestionsListViewModel::class.java)
        setContentView(mViewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
        mFetchQuestionsListUseCase.registerListener(this)
        if (mQuestionsListViewModel.questions.isEmpty()) {
            mFetchQuestionsListUseCase.fetchQuestionsAndNotify(NUM_OF_QUESTIONS_TO_FETCH)
            Toast.makeText(this, "fetching from use case", Toast.LENGTH_SHORT).show()
        } else {
            mViewMvc.bindQuestions(mQuestionsListViewModel.questions)
            Toast.makeText(this, "from ViewModel", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unregisterListener(this)
        mFetchQuestionsListUseCase.unregisterListener(this)
    }

    override fun onFetchOfQuestionsSucceeded(question: List<Question>) {
        mQuestionsListViewModel.questions = question
        mViewMvc.bindQuestions(question)
    }

    override fun onFetchOfQuestionsFailed() {
        mDialogsManager.showDialogWithId(ServerErrorDialogFragment.newInstance(), "")
    }

    override fun onQuestionClicked(question: Question) {
        QuestionDetailsActivity.start(this@QuestionsListActivity, question.id)
    }

    companion object {
        private const val NUM_OF_QUESTIONS_TO_FETCH = 20
    }
}