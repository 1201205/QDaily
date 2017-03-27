package com.hyc.qdaily.contract

import com.hyc.qdaily.base.BaseView
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.view.ViewData

/**
 * Created by ray on 17/3/15.
 */
interface LabContract{
    interface View: BaseView {
        fun showRecommendData(data:ArrayList<ViewData<*>>)
        fun showMore(data: ArrayList<ViewData<*>>)
        fun show()
    }
    interface Presenter{
        fun getLab()
        fun getMoreLab(index:String)
    }
    interface Model<T>{
        fun revertToViewData(bean: BaseBean<T>):ArrayList<ViewData<*>>
        fun addToViewData(bean: BaseBean<T>)
        fun getViewDatas():ArrayList<ViewData<*>>
    }
}