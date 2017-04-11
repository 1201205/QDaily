package com.hyc.qdaily.view.activity

import android.animation.Animator
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.base.BaseAnimatorListener
import com.hyc.qdaily.beans.paper.Question
import com.hyc.qdaily.contract.GuessContract
import com.hyc.qdaily.presenter.GuessPresenter
import com.hyc.qdaily.util.AppUtil
import com.hyc.qdaily.util.getResourceColor
import com.hyc.qdaily.util.loadUrl

/**
 * Created by hyc on 2017/4/10.
 */
class GuessActivity : BaseActivity<GuessPresenter>(), GuessContract.View {
    @BindView(R.id.tv_name1)
    private lateinit var tvName1: TextView
    @BindView(R.id.tv_name2)
    private lateinit var tvName2: TextView
    @BindView(R.id.tv_name3)
    private lateinit var tvName3: TextView
    @BindView(R.id.tv_name4)
    private lateinit var tvName4: TextView
    @BindView(R.id.sdv_1)
    private lateinit var sdv1: SimpleDraweeView
    @BindView(R.id.sdv_2)
    private lateinit var sdv2: SimpleDraweeView
    @BindView(R.id.sdv_3)
    private lateinit var sdv3: SimpleDraweeView
    @BindView(R.id.sdv_4)
    private lateinit var sdv4: SimpleDraweeView
    @BindView(R.id.ll1)
    private lateinit var ll1: LinearLayout
    @BindView(R.id.ll2)
    private lateinit var ll2: LinearLayout
    @BindView(R.id.ll3)
    private lateinit var ll3: LinearLayout
    @BindView(R.id.ll4)
    private lateinit var ll4: LinearLayout
    @BindView(R.id.tv_title)
    private lateinit var tvTitle: TextView
    private var mID = ""

    override fun onNetError() {
    }

    override fun showChoice(question: Question, count: Int, index: Int) {
        tvTitle.text = question.title
        tvName1.text = question.options!![0].title
        tvName2.text = question.options!![1].title
        tvName3.text = question.options!![2].title
        tvName4.text = question.options!![3].title
        loadUrl(sdv1, question.options!![0].image)
        loadUrl(sdv2, question.options!![1].image)
        loadUrl(sdv3, question.options!![2].image)
        loadUrl(sdv4, question.options!![3].image)
    }

    override fun showWrong(position: Int, right: Int) {

    }

    override fun showRight(position: Int) {
        var target:View? = null
        when(position){
            0->target=ll1
            1->target=ll2
            2->target=ll3
            3->target=ll4
        }
        target!!.setBackgroundColor(getResourceColor(R.color.banner_text))
        if (target!=ll1) {
            ll1.animate().alpha(0f).setDuration(300).start()
        }
        if (target!=ll2) {
            ll2.animate().alpha(0f).setDuration(300).start()
        }
        if (target!=ll3) {
            ll3.animate().alpha(0f).setDuration(300).start()
        }
        if (target!=ll4) {
            ll4.animate().alpha(0f).setDuration(300).start()
        }
        target.animate().alpha(0f).setDuration(400).setStartDelay(50).setListener(BaseAnimatorListener())
    }

    override fun handleIntent() {
        mID = intent.getStringExtra("id")
    }

    override val layoutID: Int = R.layout.activity_guess

    override fun initPresenterAndData() {
        mPresenter = GuessPresenter(this)
        mPresenter!!.getChoices(mID)
    }

    override fun initView() {
    }

}