package com.hyc.qdaily

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import butterknife.BindView
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.ViewData
import com.hyc.qdaily.beans.home.Home
import com.hyc.qdaily.contract.HomeContract
import com.hyc.qdaily.presenter.HomePresenter
import com.hyc.qdaily.view.adpter.ViewAdapter

class MainActivity : BaseActivity<HomePresenter>(),HomeContract.View {
    lateinit var mRecyclerView:RecyclerView
    private var mAdapter:ViewAdapter?=null
    override fun isSupportSwipeBack(): Boolean {
        return false
    }

    override fun showRecommendData(data: ArrayList<ViewData>) {
        mAdapter=ViewAdapter.Builder(this,data).build()
        mRecyclerView.adapter=mAdapter
        var manager=LinearLayoutManager(this)
        manager.orientation= OrientationHelper.VERTICAL
        mRecyclerView.layoutManager=manager
    }

    override fun showMore(data:ArrayList<ViewData>) {
    }

    override fun handleIntent() {
    }

    override val layoutID=R.layout.activity_main

    override fun initPresenterAndData() {
        mPresenter= HomePresenter(this)
        mPresenter.getRecommendData()
    }

    override fun initView() {
        mRecyclerView= findViewById(R.id.target) as RecyclerView
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
