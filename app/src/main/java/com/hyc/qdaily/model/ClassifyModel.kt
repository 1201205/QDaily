package com.hyc.qdaily.model

import android.util.Log
import com.hyc.qdaily.beans.other.LeftSideBar
import com.hyc.qdaily.beans.view.ViewData

/**
 * Created by hyc on 2017/3/27.
 */
class ClassifyModel private constructor() {

    var mList: ArrayList<ViewData<*>> = ArrayList()

    companion object {
        val instance: ClassifyModel = ClassifyModel()
    }

    fun convertToViewData(leftSideBars: ArrayList<LeftSideBar>?): ArrayList<ViewData<*>> {
        leftSideBars?.forEach {
            var view = ViewData<LeftSideBar>()
            view.content = it
            view.type = "classify"
            mList.add(view)
        }
        return mList
    }

    fun getLeftSideBars(): ArrayList<ViewData<*>> {
        return mList
    }
}