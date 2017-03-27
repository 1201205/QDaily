package com.hyc.qdaily.model

import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.other.Category
import com.hyc.qdaily.beans.view.ViewData

/**
 * Created by hyc on 17/3/27.
 */
class CategoryModel {
    private var mData = ArrayList<ViewData<*>>()

    fun convertToViewData(bean: BaseBean<Category>): ArrayList<ViewData<*>> {
        mData.addAll(revertNormal(bean.response!!))
        return mData
    }

    fun revertNormal(category: Category): ArrayList<ViewData<Feed>> {
        var viewData = ArrayList<ViewData<Feed>>()
        category?.feeds?.let {
            category.feeds_ad?.let {
                category.feeds!!.addAll(category.feeds_ad!!)
            }
            category.feeds!!.forEach {
                var data = ViewData<Feed>()
                when (it.type) {
                    0 -> data.type = "curiosity"
                    1 -> data.type = "feed"
                    2 -> data.type = "vertical"
                }
                data.content=it
                viewData.add(data)
            }
        }
        return viewData
    }
}