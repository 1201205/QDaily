package com.hyc.qdaily.contract

import com.hyc.qdaily.base.BaseView
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.Home

/**
 * Created by hyc on 2017/3/6.
 */

interface HomeContract{
    interface View:BaseView{
        fun showRecommendData(data:BaseBean<Home>)
        fun showMore(data: BaseBean<Home>)
    }
    interface Presenter{
        fun getRecommendData()
        fun getMoreData(index:String)
    }
}
