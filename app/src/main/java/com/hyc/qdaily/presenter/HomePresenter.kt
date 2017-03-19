package com.hyc.qdaily.presenter

import android.text.TextUtils
import android.util.Log
import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.beans.view.ColumnData
import com.hyc.qdaily.contract.HomeContract
import com.hyc.qdaily.model.MainPageModel
import com.hyc.qdaily.net.RequestClient

/**
 * Created by hyc on 2017/3/7.
 */
class HomePresenter(mView: HomeContract.View) : HomeContract.Presenter, BasePresenter<HomeContract.View>(mView) {
    override fun getMoreColumnData(id: String) {
        var data=model.getColumn(id)
        data?.let {
            getColumn(data,false)
        }
    }

    private var model: MainPageModel = MainPageModel()
    private var mLastIndex: String? = null
    override fun getMoreData() {
        RequestClient.api.getHomeDataByIndex(mLastIndex!!).compose(SchedulerTransformer.create()).map {
            mLastIndex = it.response?.last_key
            model.revertToViewData(it)
        }.subscribe({ mView.showMore(it) },
                { onError(it) }
        )
    }

    override fun getRecommendData() {
        RequestClient.api.getHomeDataByIndex("0").compose(SchedulerTransformer.create()).map {
            var c = it.response?.columns?.get(0)
            with(c) {
                c?.let {
                    var data=ColumnData()
                    data.id=c?.id.toString()
                    data.name=c.name
                    data.icon=c.icon
                    data.showType=c.show_type
                    getColumn(data,true)
                }
            }
            mLastIndex = it.response?.last_key
            if (TextUtils.isEmpty(mLastIndex)) {
                mView.noMore()
            }
            model.revertToViewData(it)
        }.subscribe({
            mView.showRecommendData(it)
        }, { onError(it) })

    }

    fun getColumn(data: ColumnData) {
        RequestClient.api.getColumn(data.id!!, data.lastIndex).compose(SchedulerTransformer.create()).subscribe({
            model.addColunm(it.response, data)
            mView.show()
        }, {
            onError(it)
        })
    }
    fun getColumn(data: ColumnData,first:Boolean) {
        RequestClient.api.getColumn(data.id!!, data.lastIndex).compose(SchedulerTransformer.create()).subscribe({
            var count=0
            if (!first) {
                count= data.feeds?.size!!
            }
            model.addColunm(it.response, data)
            if (!first) {
                mView.showMoreColumn(data.index!!, count)
            } else {
                mView.show()
            }
        }, {
            onError(it)
        })
    }
}