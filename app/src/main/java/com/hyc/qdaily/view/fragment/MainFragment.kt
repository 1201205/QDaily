package com.hyc.qdaily.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnScrollListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import com.hyc.qdaily.R
import com.hyc.qdaily.anko.LabFragmentUI
import com.hyc.qdaily.base.BaseFragment
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.HomeContract
import com.hyc.qdaily.events.JumpArticleEvent
import com.hyc.qdaily.events.LoadMoreEventX
import com.hyc.qdaily.presenter.HomePresenter
import com.hyc.qdaily.util.VerticalSpaceDecoration
import com.hyc.qdaily.util.dip2px
import com.hyc.qdaily.view.activity.ArticleActivity
import com.hyc.qdaily.view.adpter.ViewAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.find

/**
 * Created by ray on 17/3/15.
 */
class MainFragment : BaseFragment<HomePresenter>(), HomeContract.View {
    @BindView(R.id.target)
    lateinit var mRecyclerView: RecyclerView
    private var mAdapter: ViewAdapter? = null
    private lateinit var mScrollListener: OnScrollListener
    private var hasMore: Boolean = true
    private var requesting: Boolean = false
    private lateinit var mManager: LinearLayoutManager
    override fun showRecommendData(data: ArrayList<ViewData<*>>) {
        mAdapter = ViewAdapter.Builder(activity, data).build()
        mRecyclerView.adapter = mAdapter
        mManager = LinearLayoutManager(activity)
        mManager.orientation = OrientationHelper.VERTICAL
        mRecyclerView.layoutManager = mManager
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LabFragmentUI().bind(context)
        mRecyclerView = view.find(R.id.target)
        return view
    }
    override fun initPresenter() {
        mPresenter = HomePresenter(this)
        mPresenter!!.getRecommendData()
    }

    override fun showMore(data: ArrayList<ViewData<*>>) {
        var position = mAdapter?.itemCount
        mAdapter?.notifyItemInserted(position!!)
        requesting = false

    }

    override fun show() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        mScrollListener = object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (hasMore && !requesting && mManager.findLastVisibleItemPosition() >= mAdapter!!.itemCount - 6) {
                    mPresenter?.getMoreData()
                    requesting = true
                }
            }
        }
        mRecyclerView.addOnScrollListener(mScrollListener)
        mRecyclerView.addItemDecoration(VerticalSpaceDecoration(0, dip2px(3f).toInt()))
    }

    override fun onNetError() {

    }

    override fun noMore() {
        hasMore = false
        requesting = false
    }

    override fun onDetach() {
        mRecyclerView.removeOnScrollListener(mScrollListener)
        EventBus.getDefault().unregister(this)
        super.onDetach()
    }

    override fun showMoreColumn(index: Int, count: Int) {
        var v = mManager.findViewByPosition(index)
        if (v is RecyclerView) {
            v.adapter.notifyItemInserted(count)
        } else {
            if (v is ViewGroup) {
                var count = v.childCount
                (0..count - 1)
                    .filter { v.getChildAt(it) is RecyclerView }
                    .forEach {
                        (v.getChildAt(it) as RecyclerView).adapter.notifyItemInserted(count)
                    }
            }
        }
    }

    @Subscribe
    fun handleLoadMore(event: LoadMoreEventX) {
        mPresenter?.getMoreColumnData(event.id)
    }

    @Subscribe
    fun handleJumpArticle(event: JumpArticleEvent) {
        jumpToArticle(event.url)
    }

    fun jumpToArticle(url: String?) {
        var intent = Intent(context, ArticleActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}