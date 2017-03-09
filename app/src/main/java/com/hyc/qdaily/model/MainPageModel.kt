package com.hyc.qdaily.model

import android.util.Log
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.ViewData
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.home.Home
import com.hyc.qdaily.contract.HomeContract

/**
 * Created by hyc on 2017/3/9.
 */
class MainPageModel() : HomeContract.Model<Home>{
    override fun getViewDatas(): ArrayList<ViewData> {
       return datas
    }
    private var datas: ArrayList<ViewData> = ArrayList()
    override fun revertToViewData(bean: BaseBean<Home>):ArrayList<ViewData> {
        var home=bean.response
        var viewData=ViewData()
        viewData.type="banner"
        viewData.banners=(home?.banners as ArrayList<Feed>)
        Log.e("hyc-e","----"+viewData?.banners?.size)
        Log.e("hyc-e?????","----"+home.banners_ad?.size)
        home.banners_ad?.let{
//            if (it.isNotEmpty()) {
                viewData.banners!!.addAll(it)
//            }
        }
        datas.add(viewData)
        return datas
    }

    override fun addAndAddToViewData(bean: BaseBean<Home>) {
    }



}