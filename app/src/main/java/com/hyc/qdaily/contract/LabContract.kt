package com.hyc.qdaily.contract

import com.hyc.qdaily.base.BaseView
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.view.ViewData

/**
 * Created by ray on 17/3/15.
 */
interface LabContract{
    interface View: BaseView {
        fun showRecommendData(data:ArrayList<ViewData>)
        fun showMore(data: ArrayList<ViewData>)
        fun show()
    }
    interface Presenter{
        fun getRecommendData()
        fun getMoreData(index:String)
    }
    interface Model<T>{
        fun revertToViewData(bean: BaseBean<T>):ArrayList<ViewData>
        fun addAndAddToViewData(bean: BaseBean<T>)
        fun getViewDatas():ArrayList<ViewData>
    }
}