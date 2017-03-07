package com.hyc.qdaily

import android.os.Bundle
import android.util.Log
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.Home
import com.hyc.qdaily.contract.HomeContract
import com.hyc.qdaily.presenter.HomePresenter

class MainActivity : BaseActivity<HomePresenter>(),HomeContract.View {
    override fun showRecommendData(data: BaseBean<Home>) {
        data.response?:let{
            Log.e("hyc-test","null")
        }
        data.response?.let {
            Log.e("hyc-test","data.response"+data.response)
        }
    }

    override fun showMore(data: BaseBean<Home>) {
    }

    override fun handleIntent() {
    }

    override val layoutID=R.layout.activity_main

    override fun initPresenterAndData() {
        mPresenter= HomePresenter(this)
        mPresenter.getRecommendData()
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        RequestClient.api.getArticleByIndex("34566").compose(SchedulerTransformer.create<BaseBean<Article>>()).subscribeWith(
//            object : BaseNetDisposableObserver<BaseBean<Article>>() {
//                override fun onNext(articleBaseBean: BaseBean<Article>) {
//                    super.onNext(articleBaseBean)
//                }
//            })
//        RequestClient.api.getHomeDataByIndex("0").compose(SchedulerTransformer.create()).subscribe({ homeBaseBean -> Log.e("hyc-test", homeBaseBean.response?.banners?.size.toString() + "-----" + Thread.currentThread().id + "-----" + mainLooper.thread.id) })
//        RequestClient.api.getHomeDataByIndex("0").subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread()).subscribe(Consumer<BaseBean<Home>> { homeBaseBean -> Log.e("hyc-test", homeBaseBean.response?.banners?.size.toString() + "-----" + Thread.currentThread().id + "-----" + mainLooper.thread.id) }, Consumer<Throwable> { }, Action { })
    }
}
