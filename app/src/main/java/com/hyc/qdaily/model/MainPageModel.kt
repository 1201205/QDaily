package com.hyc.qdaily.model

import android.util.Log
import android.view.View
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.ColumnContent
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.home.Home
import com.hyc.qdaily.beans.view.ColumnData
import com.hyc.qdaily.contract.HomeContract

/**
 * Created by hyc on 2017/3/9.
 */
class MainPageModel() : HomeContract.Model<Home> {
    override fun getViewDatas(): ArrayList<ViewData> {
        return datas
    }

    private var datas: ArrayList<ViewData> = ArrayList()
    override fun revertToViewData(bean: BaseBean<Home>): ArrayList<ViewData> {
        var home = bean.response
        var viewData = ViewData()
        var headLineId= home?.headline?.post?.id
        Log.e("hyc-headLineId","---"+headLineId)
        viewData.type = "banner"
        viewData.banners = (home?.banners as ArrayList<Feed>)
        home?.feeds!!.forEach { content -> }
        Log.e("hyc-e", "----" + viewData?.banners?.size)
        Log.e("hyc-e?????", "----" + home.banners_ad?.size)
        home.banners_ad?.let {
            var i: Int = 1
            var indexParm = viewData.banners!!.size / (home.banners_ad!!.size * 2)
            if (indexParm == 0) {
                indexParm = 1
            }
            it.forEach {
                viewData.banners!!.add(i * indexParm, it)
                i++
            }
        }
        datas.add(viewData)
        home?.feeds?.let {
            home.feeds!!.forEach {
                var data = ViewData()
                when (it.type) {
                    0 -> data.type = "curiosity"
                    1 -> data.type = "feed"
                    2 -> data.type = "vertical"
                }
                if (headLineId!=0||it.post?.id!=headLineId) {
                    data.feed = it
                    datas.add(data)
                }
            }
        }
        if (headLineId!=null&&headLineId!=0) {
            var headLine=ViewData()
            headLine.headLine=home.headline
            headLine.type="headline"
            datas.add(1,headLine)
        }

        return datas
    }

    override fun addAndAddToViewData(bean: BaseBean<Home>) {
    }

    fun addColunm(bean: ColumnContent?, name: String, ico: String,showType:Int) {
        var data = ViewData()
        var colunmData = ColumnData()
        with(colunmData) {
            icon = ico
            colunmData.name = name
            feeds = ArrayList()
            var type:String
            when(showType){
                1->type="language"
                2->type="book"
                else->type="language"
            }
            bean?.feeds?.forEach {
                var viewData = ViewData()
                viewData.type = type
                viewData.feed = it
                feeds!!.add(viewData)
            }
        }
        data.type = "recycler"
        data.columnContent = colunmData
        datas.add(4, data)

    }

}