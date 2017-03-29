package com.hyc.qdaily.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyc.qdaily.R
import com.hyc.qdaily.base.BaseFragment
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.LabContract
import com.hyc.qdaily.events.JumpPaperDetailEvent
import com.hyc.qdaily.presenter.LabPresenter
import com.hyc.qdaily.util.VerticalSpaceDecoration
import com.hyc.qdaily.util.dip2px
import com.hyc.qdaily.view.activity.SayActivity
import com.hyc.qdaily.view.activity.VoteActivity
import com.hyc.qdaily.view.adpter.ViewAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by ray on 17/3/15.
 */
class LabFragment : BaseFragment<LabPresenter>(), LabContract.View {

    lateinit var mRecyclerView: RecyclerView
    private var mAdapter: ViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_main, container, false)
        mRecyclerView = view.findViewById(R.id.target) as RecyclerView
        return view
    }

    override fun showRecommendData(data: ArrayList<ViewData<*>>) {
        mAdapter = ViewAdapter.Builder(activity, data).build()
        mRecyclerView.adapter = mAdapter
        var manager = LinearLayoutManager(activity)
        manager.orientation = OrientationHelper.VERTICAL
        mRecyclerView.layoutManager = manager
        mRecyclerView.addItemDecoration(VerticalSpaceDecoration(0, dip2px(3f).toInt()))

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun jumpToDetail(event:JumpPaperDetailEvent){
        //1000 投票  1001 我说  1002 测试 1003 你猜
        when(event.mType){
            1000->{startActivity(VoteActivity.getIntent(activity,event.mID))}
            1001->{startActivity(SayActivity.getIntent(activity,event.mID))}
            1002->{}
            1003->{}
        }
    }
    override fun showMore(data: ArrayList<ViewData<*>>) {
    }

    override fun show() {
    }

    override fun initPresenter() {
        mPresenter = LabPresenter(this)
        mPresenter!!.getLab()
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
    }

    override fun onNetError() {
    }
}