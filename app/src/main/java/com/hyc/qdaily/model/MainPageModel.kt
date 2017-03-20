package com.hyc.qdaily.model

import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.ColumnContent
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.home.Home
import com.hyc.qdaily.beans.view.ColumnData
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.HomeContract

/**
 * Created by hyc on 2017/3/9.
 */
class MainPageModel : HomeContract.Model<Home> {
    override fun getViewDatas(): ArrayList<ViewData> {
        return datas
    }
    private var columnDatas:ArrayList<ColumnData> = ArrayList()

    private var datas: ArrayList<ViewData> = ArrayList()
    override fun revertToViewData(bean: BaseBean<Home>): ArrayList<ViewData> {
        var home = bean.response
        if (home?.banners?.size!! > 0) {
            datas.add(revertBanner(home!!))
        }
        var headLineId = home?.headline?.post?.id
        datas.addAll(revertNormal(home, headLineId))
        if (headLineId != null && headLineId != 0) {
            datas.add(revertHeadLine(home))
        }

        return datas
    }

    fun revertBanner(home: Home): ViewData {
        var viewData = ViewData()
        viewData.type = "banner"
        viewData.banners = (home?.banners as ArrayList<Feed>)
        if (home.banners_ad != null && home.banners_ad?.size!! > 0) {
            var i: Int = 1
            var indexParm = viewData.banners!!.size / (home.banners_ad!!.size * 2)
            if (indexParm == 0) {
                indexParm = 1
            }
            home.banners_ad!!.forEach {
                viewData.banners!!.add(i * indexParm, it)
                i++
            }
        }
        return viewData
    }

    fun revertHeadLine(home: Home): ViewData {
        var headLine = ViewData()
        headLine.headLine = home.headline
        headLine.type = "headline"
        return headLine
    }

    fun revertNormal(home: Home, id: Int?): ArrayList<ViewData> {
        var viewDatas = ArrayList<ViewData>()
        home?.feeds?.let {
            home.feeds!!.forEach {
                var data = ViewData()
                when (it.type) {
                    0 -> data.type = "curiosity"
                    1 -> data.type = "feed"
                    2 -> data.type = "vertical"
                }
                if (id != 0 || it.post?.id != id) {
                    data.feed = it
                    viewDatas.add(data)
                }
            }
        }
        return viewDatas
    }

    override fun addToViewData(bean: BaseBean<Home>) {

    }

    fun addColunm(bean: ColumnContent?, colunmData: ColumnData) {
        with(colunmData) {
            if (feeds==null) {
                feeds = ArrayList()
            }
            var type: String
            when (showType) {
                1 -> type = "language"
                2 -> type = "book"
                else -> type = "language"
            }
            bean?.feeds?.forEach {
                var viewData = ViewData()
                viewData.type = type
                viewData.feed = it
                feeds!!.add(viewData)
            }
            colunmData.lastIndex=bean?.last_key
        }
        if (columnDatas.contains(colunmData)) {
            return
        }
        var data = ViewData()
        data.type = "recycler"
        data.columnContent = colunmData
        datas.add(0, data)
        columnDatas.add(colunmData)

    }

    fun getColumn(id:String):ColumnData?{
        columnDatas.forEach {
            if (it.id.equals(id)) {
                return it
            }
        }
        return null
    }

}