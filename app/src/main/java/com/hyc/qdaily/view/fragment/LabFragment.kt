package com.hyc.qdaily.view.fragment

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
import com.hyc.qdaily.presenter.LabPresenter
import com.hyc.qdaily.view.adpter.ViewAdapter

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

    override fun showRecommendData(data: ArrayList<ViewData>) {
        mAdapter = ViewAdapter.Builder(activity, data).build()
        mRecyclerView.adapter = mAdapter
        var manager = LinearLayoutManager(activity)
        manager.orientation = OrientationHelper.VERTICAL
        mRecyclerView.layoutManager = manager
    }

    override fun showMore(data: ArrayList<ViewData>) {
    }

    override fun show() {
    }

    override fun initPresenter() {
        mPresenter = LabPresenter(this)
        mPresenter!!.getLab()
    }

}