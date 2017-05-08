package com.hyc.qdaily.view.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.TextUtils
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.anko.SayActivityUI
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.SayContract
import com.hyc.qdaily.presenter.SayPresenter
import com.hyc.qdaily.view.adpter.ViewAdapter
import org.jetbrains.anko.find

/**
 * Created by hyc on 17/3/28.
 */
class SayActivity : BaseActivity<SayPresenter>(), SayContract.View {
    override fun generateView(): View {
        var v = SayActivityUI().bind(this)
        rvTarget = v.find(R.id.rv_target)
        return v
    }
    override fun showContent(data: ArrayList<ViewData<*>>) {
        mAdapter = ViewAdapter.Builder(this, data).build()
        rvTarget.adapter = mAdapter
    }

    override fun showMore(data: ArrayList<ViewData<*>>) {
        var position = mAdapter?.itemCount
        mAdapter?.notifyItemInserted(position!!)
        requesting = false
    }

    override fun noMore() {
        requesting=false
        hasMore=false
    }

    lateinit var rvTarget: RecyclerView
    private var mID: String? = null
    private var mAdapter: ViewAdapter? = null
    private var hasMore=true
    private var requesting=false
    private var mManager:StaggeredGridLayoutManager?=null

    companion object {
        private val ID = "id"
        fun getIntent(context: Context, id: String): Intent {
            var intent = Intent(context, SayActivity::class.java)
            intent.putExtra(ID, id)
            return intent
        }
    }

    override fun onNetError() {
    }

    override fun handleIntent() {
        mID = intent.getStringExtra(ID)
    }

    override val layoutID: Int = R.layout.activity_vote

    override fun initPresenterAndData() {
        if (TextUtils.isEmpty(mID)) {
            return
        }
        mPresenter = SayPresenter(this)
        mPresenter!!.getContent(mID!!)
    }

    override fun initView() {
        mManager=StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvTarget.layoutManager = mManager
        rvTarget.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                var positionArray=IntArray(2)
                if (hasMore && !requesting && mManager!!.findLastVisibleItemPositions(positionArray)[1] >= mAdapter!!.itemCount - 6) {
                    mPresenter!!.getOption()
                    requesting = true
                }
            }
        })
    }

}