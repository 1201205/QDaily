package com.hyc.qdaily.view.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.hyc.qdaily.R
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.CategoryContract
import com.hyc.qdaily.presenter.CategoryPresernter
import com.hyc.qdaily.util.VerticalSpaceDecoration
import com.hyc.qdaily.util.dip2px
import com.hyc.qdaily.view.adpter.ViewAdapter

/**
 * Created by hyc on 17/3/27.
 */
class CategoryActivity : BaseActivity<CategoryPresernter>(), CategoryContract.View {
    @BindView(R.id.tv_title)
    lateinit var tvTitle: TextView
    @BindView(R.id.target)
    lateinit var rvTarget: RecyclerView
    private var mManager: LinearLayoutManager? = null
    private var mAdapter: ViewAdapter? = null
    private var hasMore: Boolean = true
    private var requesting = false
    private var mID: String = ""
    private var mTitle: String = ""


    companion object {
        private val ID = "id"
        private val TITLE = "title"
        fun getIntent(context: Context, id: String, title: String): Intent {
            var intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra(ID, id)
            intent.putExtra(TITLE, title)
            return intent
        }
    }

    override fun onNetError() {
    }

    override fun showCategory(data: ArrayList<ViewData<*>>) {
        mAdapter = ViewAdapter.Builder(this, data).build();
        rvTarget.adapter = mAdapter
        mManager = LinearLayoutManager(this)
        rvTarget.layoutManager = mManager
    }

    override fun showMore(data: ArrayList<ViewData<*>>) {
        var position = mAdapter?.itemCount
        mAdapter?.notifyItemInserted(position!!)
        requesting = false
    }

    override fun noMore() {
        hasMore = false
    }

    override fun handleIntent() {
        mID = intent.getStringExtra(ID)
        mTitle = intent.getStringExtra(TITLE)
    }

    override val layoutID: Int = R.layout.activity_category

    override fun initPresenterAndData() {
        mPresenter = CategoryPresernter(this)
        mPresenter!!.getCategory(mID)
    }

    override fun initView() {
        tvTitle.text = mTitle
        rvTarget.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (hasMore && !requesting && mManager!!.findLastVisibleItemPosition() >= mAdapter!!.itemCount - 6) {
                    mPresenter?.getMoreData()
                    requesting = true
                }
            }
        })
        rvTarget.addItemDecoration(VerticalSpaceDecoration(0, dip2px(3f).toInt()))
    }

    @OnClick(R.id.iv_back)
    fun back() {
        finish()
    }
}