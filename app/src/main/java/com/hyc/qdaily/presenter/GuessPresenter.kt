package com.hyc.qdaily.presenter

import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.beans.paper.Question
import com.hyc.qdaily.contract.GuessContract
import com.hyc.qdaily.net.RequestClient

/**
 * Created by hyc on 2017/4/10.
 */
class GuessPresenter(view: GuessContract.View) : BasePresenter<GuessContract.View>(view), GuessContract.Presenter {
    private var mQuestions: ArrayList<Question>? = null
    private var mCurrentQuestion: Question? = null
    private var mCount: Int = 0

    override fun getChoices(id: String) {
        RequestClient.api.getChoices(id).compose(SchedulerTransformer.create()).subscribe({
            mQuestions = it.response?.normal_questions
            if (mQuestions == null || mQuestions!!.size == 0) {
                return@subscribe
            }
            mCount = mQuestions!!.size
            mCurrentQuestion = mQuestions!![0]
            mQuestions!!.removeAt(0)
            mView.showChoice(mCurrentQuestion!!, mCount, mCount - mQuestions!!.size)
        }, { onError(it) })
    }

    override fun showNextChoice() {
        if (mQuestions!!.size == 0) {
            commitChoices()
            return
        }
        mCurrentQuestion = mQuestions!![0]
        mQuestions!!.removeAt(0)
        mView.showChoice(mCurrentQuestion!!, mCount, mCount - mQuestions!!.size)

    }

    override fun commitChoices() {
    }

    override fun onChoiceClicked(position: Int) {
        if (mCurrentQuestion!!.options!![position].score > 0) {
            mView.showRight(position)
        } else {
            var right = 0
            var index = 0
            for (option in mCurrentQuestion!!.options!!) {
                if (option.score > 0) {
                    right = index
                    break
                }
                index++
            }
            mView.showWrong(position, right)
        }
    }

}