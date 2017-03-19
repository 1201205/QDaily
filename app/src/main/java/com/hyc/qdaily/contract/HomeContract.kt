package com.hyc.qdaily.contract

import com.hyc.qdaily.base.BaseView
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.beans.home.Home

/**
 * Created by hyc on 2017/3/6.
 */

interface HomeContract{
    interface View:BaseView{
        fun showRecommendData(data:ArrayList<ViewData>)
        fun showMore(data: ArrayList<ViewData>)
        fun show()
        fun noMore()
        fun showMoreColumn(index:Int,count:Int)
    }
    interface Presenter{
        fun getRecommendData()
        fun getMoreData()
        fun getMoreColumnData(id:String)
    }
    interface Model<T>{
        fun revertToViewData(bean:BaseBean<T>):ArrayList<ViewData>
        fun addToViewData(bean: BaseBean<T>)
        fun getViewDatas():ArrayList<ViewData>
    }
}
