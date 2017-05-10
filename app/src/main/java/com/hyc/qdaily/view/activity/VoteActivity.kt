package com.hyc.qdaily.view.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import butterknife.BindView
import com.hyc.qdaily.R
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.VoteContract
import com.hyc.qdaily.presenter.VotePresenter
import com.hyc.qdaily.view.adpter.ViewAdapter

/**
 * Created by ray on 17/3/28.
 */
class VoteActivity : BaseActivity<VotePresenter>(), VoteContract.View {
    @BindView(R.id.rv_target)
    lateinit var rvTarget: RecyclerView

    private var mID: String? = null

    companion object {
        private val ID = "id"
        fun getIntent(context: Context, id: String): Intent {
            var intent = Intent(context, VoteActivity::class.java)
            intent.putExtra(ID, id)
            return intent
        }
    }

    override fun onNetError() {
    }

    override fun showVote(data: ArrayList<ViewData<*>>) {
        rvTarget.adapter=ViewAdapter.Builder(this,data).build()
    }

    override fun handleIntent() {
        mID = intent.getStringExtra(ID)
    }

    override val layoutID: Int = R.layout.activity_vote

    override fun initPresenterAndData() {
        if (TextUtils.isEmpty(mID)) {
            return
        }
        mPresenter = VotePresenter(this)
        mPresenter!!.getVoteByID(mID!!)
    }

    override fun initView() {
        rvTarget.layoutManager=LinearLayoutManager(this)
    }

}