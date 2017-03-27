package com.hyc.qdaily.model

import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.ColumnContent
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.home.HeadLine
import com.hyc.qdaily.beans.home.Home
import com.hyc.qdaily.beans.view.ColumnData
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.HomeContract

/**
 * Created by hyc on 2017/3/9.
 */
class MainPageModel : HomeContract.Model<Home> {
    override fun getViewDatas(): ArrayList<ViewData<*>> {
        return data
    }
    private var columnData:ArrayList<ColumnData> = ArrayList()

    private var data: ArrayList<ViewData<*>> = ArrayList()
    override fun revertToViewData(bean: BaseBean<Home>): ArrayList<ViewData<*>> {
        var home = bean.response
        if (home?.banners?.size!! > 0) {
            data.add(revertBanner(home!!))
        }
        var headLineId = home?.headline?.post?.id
        if (headLineId != null && headLineId != 0) {
            data.add(revertHeadLine(home))
        }
        data.addAll(revertNormal(home, headLineId))
        return data
    }

    fun revertBanner(home: Home): ViewData<*> {
        var viewData = ViewData<ArrayList<Feed>>()
        viewData.type = "banner"
        viewData.content = (home?.banners as ArrayList<Feed>)
        if (home.banners_ad != null && home.banners_ad?.size!! > 0) {
            var i: Int = 1
            var indexParam = viewData.content!!.size / (home.banners_ad!!.size * 2)
            if (indexParam == 0) {
                indexParam = 1
            }
            home.banners_ad!!.forEach {
                viewData.content!!.add(i * indexParam, it)
                i++
            }
        }
        return viewData
    }

    fun revertHeadLine(home: Home): ViewData<HeadLine> {
        var headLine = ViewData<HeadLine>()
        headLine.content = home.headline
        headLine.type = "headline"
        return headLine
    }

    fun revertNormal(home: Home, id: Int?): ArrayList<ViewData<Feed>> {
        var viewDatas = ArrayList<ViewData<Feed>>()
        home?.feeds?.let {
            home.feeds!!.forEach {
                var data = ViewData<Feed>()
                when (it.type) {
                    0 -> data.type = "curiosity"
                    1 -> data.type = "feed"
                    2 -> data.type = "vertical"
                }
                if (id != 0 || it.post?.id != id) {
                    data.content = it
                    viewDatas.add(data)
                }
            }
        }
        return viewDatas
    }

    override fun addToViewData(bean: BaseBean<Home>) {

    }

    fun addColumn(bean: ColumnContent?, columnData: ColumnData) {
        with(columnData) {
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
                var viewData = ViewData<Feed>()
                viewData.type = type
                viewData.content = it
                feeds!!.add(viewData)
            }
            columnData.lastIndex=bean?.last_key
        }
        if (this.columnData.contains(columnData)) {
            return
        }
        var data = ViewData<ColumnData>()
        data.type = "recycler"
        data.content = columnData
        this.data.add(columnData.index!!, data)
        this.columnData.add(columnData)

    }

    fun getColumn(id:String):ColumnData?{
        columnData.forEach {
            if (it.id.equals(id)) {
                return it
            }
        }
        return null
    }

}