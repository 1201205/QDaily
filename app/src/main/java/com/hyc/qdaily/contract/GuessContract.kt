package com.hyc.qdaily.contract

import com.hyc.qdaily.base.BaseView
import com.hyc.qdaily.beans.paper.Choices
import com.hyc.qdaily.beans.paper.Question

/**
 * Created by hyc on 2017/4/10.
 */
interface GuessContract {
    interface View : BaseView {
        fun showChoice(question: Question, count: Int, index: Int)
        fun showWrong(position: Int,right:Int)
        fun showRight(position: Int)
    }

    interface Presenter {
        fun getChoices(id: String)
        fun getNextChoice(): Question?
        fun commitChoices()
        fun onChoiceClicked(position: Int)
    }
}