package com.hyc.qdaily.presenter

import android.util.Log
import com.hyc.qdaily.base.BaseNetDisposableObserver
import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.Home
import com.hyc.qdaily.contract.HomeContract
import com.hyc.qdaily.net.RequestClient
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * Created by hyc on 2017/3/7.
 */
class HomePresenter(mView: HomeContract.View) : HomeContract.Presenter, BasePresenter<HomeContract.View>(mView) {
    override fun getMoreData(index: String) {
    }

    override fun getRecommendData() {
//        RequestClient.api.getHomeDataByIndex("0").compose(SchedulerTransformer.create()).subscribe({ homeBaseBean ->
//            Log.e("hyc-test", "---222222---" + homeBaseBean.response?.columns?.size)
//            mView.showRecommendData(homeBaseBean)
//        }, { throwable ->
//            Log.e("hyc-test", throwable.stackTrace.toString());
//        }, {
//            Log.e("hhhhhhahhhha", "完成任务");
//        }
//        )
        RequestClient.api.getHomeDataByIndex("0").compose(SchedulerTransformer.create()).subscribe(object : BaseNetDisposableObserver<BaseBean<Home>>(true) {
            override fun onError(throwable: Throwable) {
                super.onError(throwable)
            }

            override fun onNext(t: BaseBean<Home>) {
                super.onNext(t)
                mView.showRecommendData(t)
            }

            override fun onComplete() {
                super.onComplete()
                Log.e("hhhhhhahhhha", "完成任务");
            }
        })
    }
}