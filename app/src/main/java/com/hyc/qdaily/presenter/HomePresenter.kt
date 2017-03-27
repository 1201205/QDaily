package com.hyc.qdaily.presenter

import android.text.TextUtils
import android.util.Log
import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.Home
import com.hyc.qdaily.beans.view.ColumnData
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.HomeContract
import com.hyc.qdaily.model.ClassifyModel
import com.hyc.qdaily.model.MainPageModel
import com.hyc.qdaily.net.RequestClient
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function

/**
 * Created by hyc on 2017/3/7.
 */
class HomePresenter(mView: HomeContract.View) : HomeContract.Presenter, BasePresenter<HomeContract.View>(mView) {
    private val MAX_RETRY_COUNT = 3
    private var retry = 0
    override fun getMoreColumnData(id: String) {
        var data = model.getColumn(id)
        data?.let {
            getColumn(data, false)
        }
    }

    private var revert = Function<BaseBean<Home>, ArrayList<ViewData<*>>> { it ->
        var home = it!!.response
        mLastIndex = home?.last_key
        if (TextUtils.isEmpty(mLastIndex)) {
            mView.noMore()
        }
        var index = 1
        var count = home?.columns?.size
        it.response?.columns?.forEach {
            var data = ColumnData()
            data.id = it?.id.toString()
            data.name = it.name
            data.icon = it.icon
            data.showType = it.show_type
            data.index = genColumnIndex(index, count!!, home)
            getColumn(data, true)
            index++
        }
        model.revertToViewData(it)
    }
    private var retryError = Consumer<Throwable> {
        if (retry < MAX_RETRY_COUNT) {
            getLeftBars()
        }
        retry++
    }
    private var model: MainPageModel = MainPageModel()
    private var mLastIndex: String? = null
    override fun getMoreData() {
        RequestClient.api.getHomeDataByIndex(mLastIndex!!).compose(SchedulerTransformer.create()).map(revert).subscribe({ mView.showMore(it) },
            { onError(it) }
        )
    }

    override fun getRecommendData() {
        RequestClient.api.getHomeDataByIndex("0").compose(SchedulerTransformer.create()).map(revert).subscribe({
            mView.showRecommendData(it)
        }, { onError(it) })
        getLeftBars()
    }

    private fun getLeftBars() {
        RequestClient.api.getLeftBar().compose(SchedulerTransformer.create()).map {
            ClassifyModel.instance.convertToViewData(it.response)
        }.subscribe({}, { retryError })
    }

    fun getColumn(data: ColumnData, first: Boolean) {
        data.requesting = true
        RequestClient.api.getColumn(data.id!!, data.lastIndex!!).compose(SchedulerTransformer.create()).subscribe({
            var count = 0
            if (!first) {
                count = data.feeds?.size!!
            }
            model.addColumn(it.response, data)
            if (!first) {
                mView.showMoreColumn(data.index!!, count)
            } else {
                mView.show()
            }
            data.requesting = false
        }, {
            onError(it)
            data.requesting = false
        })
    }

    fun genColumnIndex(index: Int, count: Int, home: Home?): Int {
        var size = home?.feeds?.size
        var dataSize = model.getViewDatas().size
        size?.let {
            return size.div(count) * index + dataSize
        }
        return 0
    }
}