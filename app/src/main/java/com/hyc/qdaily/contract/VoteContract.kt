package com.hyc.qdaily.contract

import com.hyc.qdaily.base.BaseView
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.paper.PaperDetail
import com.hyc.qdaily.beans.view.ViewData

/**
 * Created by hyc on 2017/3/28.
 */
interface VoteContract{
    interface View:BaseView{
        fun showVote(data:ArrayList<ViewData<*>>)
    }

    interface Presenter{
        fun getVoteByID(id:String)
    }

    interface Model{
        fun revertToViewData(bean: BaseBean<PaperDetail>?):ArrayList<ViewData<*>>
    }
}