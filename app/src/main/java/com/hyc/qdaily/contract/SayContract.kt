package com.hyc.qdaily.contract

import com.hyc.qdaily.base.BaseView
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.paper.PaperDetail
import com.hyc.qdaily.beans.paper.PaperOptions
import com.hyc.qdaily.beans.view.ViewData

/**
 * Created by hyc on 2017/3/29.
 */
interface SayContract {
    interface View : BaseView {
        fun showContent(data: ArrayList<ViewData<*>>)
        fun showMore(data: ArrayList<ViewData<*>>)
        fun noMore()
    }

    interface Presenter {
        fun getContent(id: String)
        fun getOption()
    }

    interface Model {
        fun optionToViewData(bean: BaseBean<PaperOptions>): ArrayList<ViewData<*>>
        fun revertToViewData(bean: BaseBean<PaperDetail>): ArrayList<ViewData<*>>
    }

}