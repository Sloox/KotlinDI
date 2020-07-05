package com.learnwright.kotlindi.screens.questionlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learnwright.kotlindi.R
import com.learnwright.kotlindi.di.mvcviews.BaseViewMvc
import com.learnwright.kotlindi.screens.questionlist.QuestionsListViewMvcImpl.QuestionsAdapter.QuestionViewHolder
import com.learnwright.kotlindi.usecases.questions.Question
import java.util.*

class QuestionsListViewMvcImpl(inflater: LayoutInflater, container: ViewGroup?) : BaseViewMvc<QuestionsListViewMvc.Listener>(), QuestionsListViewMvc {
    private val mRecyclerView: RecyclerView
    private val mQuestionsAdapter: QuestionsAdapter
    override fun bindQuestions(questions: List<Question>) {
        mQuestionsAdapter.bindData(questions)
    }

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    class QuestionsAdapter(private val mOnQuestionClickListener: OnQuestionClickListener) : RecyclerView.Adapter<QuestionViewHolder>() {
        private var mQuestionsList: List<Question> = ArrayList<Question>(0)

        inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var mTitle: TextView = view.findViewById(R.id.txt_title)
        }

        fun bindData(questions: List<Question>) {
            mQuestionsList = ArrayList(questions)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
            val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_question_list_item, parent, false)
            return QuestionViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
            holder.mTitle.text = mQuestionsList[position].title
            holder.itemView.setOnClickListener { mOnQuestionClickListener.onQuestionClicked(mQuestionsList[position]) }
        }

        override fun getItemCount(): Int = mQuestionsList.size
    }

    init {
        rootView = (inflater.inflate(R.layout.layout_questions_list, container, false))

        // init recycler view
        mRecyclerView = findViewById(R.id.recycler)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mQuestionsAdapter = QuestionsAdapter(object : OnQuestionClickListener {
            override fun onQuestionClicked(question: Question) {
                for (listener in getListeners()) {
                    listener.onQuestionClicked(question)
                }
            }
        })
        mRecyclerView.adapter = mQuestionsAdapter
    }
}