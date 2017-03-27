package com.hyc.qdaily.contract

import com.hyc.qdaily.base.BaseView
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.beans.home.Home

/**
 * Created by hyc on 2017/3/6.
 */

interface CategoryContract{
    interface View:BaseView{
        fun showCategory(data:ArrayList<ViewData<*>>)
        fun showMore(data: ArrayList<ViewData<*>>)
        fun noMore()
    }
    interface Presenter{
        fun getCategory(id:String)
        fun getMoreData()
    }
    interface Model<T>{
        fun revertToViewData(bean:BaseBean<T>):ArrayList<ViewData<*>>
        fun addToViewData(bean: BaseBean<T>)
        fun getViewDatas():ArrayList<ViewData<*>>
    }
}
