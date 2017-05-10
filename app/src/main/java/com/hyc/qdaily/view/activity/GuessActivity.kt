package com.hyc.qdaily.view.activity

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.base.BaseAnimatorListener
import com.hyc.qdaily.beans.paper.Question
import com.hyc.qdaily.contract.GuessContract
import com.hyc.qdaily.presenter.GuessPresenter
import com.hyc.qdaily.util.loadUrl

/**
 * Created by hyc on 2017/4/10.
 */
class GuessActivity : BaseActivity<GuessPresenter>(), GuessContract.View {
    @BindView(R.id.tv_name1)
    lateinit var tvName1: TextView
    @BindView(R.id.tv_name2)
    lateinit var tvName2: TextView
    @BindView(R.id.tv_name3)
    lateinit var tvName3: TextView
    @BindView(R.id.tv_name4)
    lateinit var tvName4: TextView
    @BindView(R.id.sdv_1)
    lateinit var sdv1: SimpleDraweeView
    @BindView(R.id.sdv_2)
    lateinit var sdv2: SimpleDraweeView
    @BindView(R.id.sdv_3)
    lateinit var sdv3: SimpleDraweeView
    @BindView(R.id.sdv_4)
    lateinit var sdv4: SimpleDraweeView
    @BindView(R.id.ll1)
    lateinit var ll1: LinearLayout
    @BindView(R.id.ll2)
    lateinit var ll2: LinearLayout
    @BindView(R.id.ll3)
    lateinit var ll3: LinearLayout
    @BindView(R.id.ll4)
    lateinit var ll4: LinearLayout
    @BindView(R.id.tv_title)
    lateinit var tvTitle: TextView
    @BindView(R.id.tv_count)
    lateinit var tvCount: TextView
    @BindView(R.id.tv_index)
    lateinit var tvIndex: TextView
    private var mID = ""
    private val ANIM_TIME_NORMAL: Long = 1200
    private val ANIM_TIME_RIGHT: Long = 1500

    override fun onNetError() {
    }

    companion object {
        private val ID = "id"
        fun getIntent(context: Context, id: String): Intent {
            var intent = Intent(context, GuessActivity::class.java)
            intent.putExtra(ID, id)
            return intent
        }
    }

    override fun showChoice(question: Question, count: Int, index: Int) {
        tvCount.text=count.toString()
        tvIndex.text=index.toString()
        ll1.alpha = 1f
        ll2.alpha = 1f
        ll3.alpha = 1f
        ll4.alpha = 1f
        ll1.animate().setListener(null)
        ll2.animate().setListener(null)
        ll3.animate().setListener(null)
        ll4.animate().setListener(null)
        ll1.setBackgroundResource(R.drawable.guess_normal_bg)
        ll2.setBackgroundResource(R.drawable.guess_normal_bg)
        ll3.setBackgroundResource(R.drawable.guess_normal_bg)
        ll4.setBackgroundResource(R.drawable.guess_normal_bg)
        tvTitle.text = question.content
        tvName1.text = question.options!![0].title
        tvName2.text = question.options!![1].title
        tvName3.text = question.options!![2].title
        tvName4.text = question.options!![3].title
        loadUrl(sdv1, question.options!![0].option_pic_url)
        loadUrl(sdv2, question.options!![1].option_pic_url)
        loadUrl(sdv3, question.options!![2].option_pic_url)
        loadUrl(sdv4, question.options!![3].option_pic_url)
    }

    override fun showWrong(position: Int, right: Int) {
        var target: View? = null
        var rightView: View? = null
        when (position) {
            0 -> target = ll1
            1 -> target = ll2
            2 -> target = ll3
            3 -> target = ll4
        }
        when (right) {
            0 -> rightView = ll1
            1 -> rightView = ll2
            2 -> rightView = ll3
            3 -> rightView = ll4
        }
        target!!.setBackgroundResource(R.drawable.guess_wrong_bg)
        if (target != ll1 && rightView != ll1) {
            ll1.animate().alpha(0f).setDuration(ANIM_TIME_NORMAL).start()
        }
        if (target != ll2 && rightView != ll2) {
            ll2.animate().alpha(0f).setDuration(ANIM_TIME_NORMAL).start()
        }
        if (target != ll3 && rightView != ll3) {
            ll3.animate().alpha(0f).setDuration(ANIM_TIME_NORMAL).start()
        }
        if (target != ll4 && rightView != ll4) {
            ll4.animate().alpha(0f).setDuration(ANIM_TIME_NORMAL).start()
        }
        rightView!!.post {
            rightView!!.setBackgroundResource(R.drawable.guess_right_bg)
        }
        target.animate().alpha(0f).setDuration(ANIM_TIME_RIGHT).start()
        rightView!!.animate().alpha(0f).setDuration(ANIM_TIME_RIGHT).setStartDelay(250).setListener(object : BaseAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?) {
                mPresenter!!.showNextChoice()
            }
        })

    }

    override fun showRight(position: Int) {
        var target: View? = null
        when (position) {
            0 -> target = ll1
            1 -> target = ll2
            2 -> target = ll3
            3 -> target = ll4
        }
        target!!.setBackgroundResource(R.drawable.guess_right_bg)
        if (target != ll1) {
            ll1.animate().alpha(0f).setDuration(ANIM_TIME_NORMAL).start()
        }
        if (target != ll2) {
            ll2.animate().alpha(0f).setDuration(ANIM_TIME_NORMAL).start()
        }
        if (target != ll3) {
            ll3.animate().alpha(0f).setDuration(ANIM_TIME_NORMAL).start()
        }
        if (target != ll4) {
            ll4.animate().alpha(0f).setDuration(ANIM_TIME_NORMAL).start()
        }
        target.animate().alpha(0f).setDuration(ANIM_TIME_RIGHT).setStartDelay(250).setListener(object : BaseAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?) {
                mPresenter!!.showNextChoice()
            }
        })
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

    @OnClick(R.id.ll1, R.id.ll2, R.id.ll3, R.id.ll4)
    fun onClick(view: View) {
        when (view.id) {
            R.id.ll1 -> mPresenter!!.onChoiceClicked(0)
            R.id.ll2 -> mPresenter!!.onChoiceClicked(1)
            R.id.ll3 -> mPresenter!!.onChoiceClicked(3)
            R.id.ll4 -> mPresenter!!.onChoiceClicked(3)

        }
    }
}