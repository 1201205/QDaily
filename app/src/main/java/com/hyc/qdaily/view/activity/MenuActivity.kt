package com.hyc.qdaily.view.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.OvershootInterpolator
import android.widget.ImageButton
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.OnClick
import com.hyc.qdaily.R
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.events.JumpCategoryEvent
import com.hyc.qdaily.model.ClassifyModel
import com.hyc.qdaily.presenter.VoidPresenter
import com.hyc.qdaily.view.adpter.ViewAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by ray on 17/3/22.
 */
class MenuActivity : BaseActivity<VoidPresenter>() {
    @BindView(R.id.ll_horizontal)
    lateinit var llHorizontal: LinearLayout
    @BindView(R.id.ll_vertical)
    lateinit var llVertical: LinearLayout
    @BindView(R.id.rv_classify)
    lateinit var rvClassify: RecyclerView
    @BindView(R.id.ll_classify)
    lateinit var llClassify: LinearLayout
    @BindView(R.id.ib_classify)
    lateinit var ibClassify: ImageButton
    @BindView(R.id.ib_back)
    lateinit var ibBack: ImageButton
    var horizontalAnim: Animator? = null
    var verticalAnim: Animator? = null
    var horizontalAnimBack: Animator? = null
    var verticalAnimBack: Animator? = null
    var leftAnim: Animator? = null
    var rightAnim: Animator? = null
    var leftAnimBack: Animator? = null
    var rightAnimBack: Animator? = null
    var interpolator: OvershootInterpolator = OvershootInterpolator(1f)
    private var classifyOpened: Boolean = false

    override val layoutID = R.layout.activity_menu
    override fun onNetError() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun handleIntent() {
    }

    override fun initPresenterAndData() {
    }

    override fun isSupportSwipeBack(): Boolean {
        return false
    }

    override fun initView() {
        llHorizontal.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                horizontalAnim ?: let {
                    llHorizontal.viewTreeObserver.removeOnPreDrawListener(this)
                    initAnim()
                    horizontalAnim?.start()
                    verticalAnim?.start()
                    llHorizontal.visibility = View.VISIBLE
                    llVertical.visibility = View.VISIBLE
                }
                return true
            }
        })
        initLeftSideBar()
    }

    private fun initLeftSideBar() {
        rvClassify.adapter = ViewAdapter.Builder(this, ClassifyModel.instance.getLeftSideBars()).build()
        rvClassify.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initAnim() {
        var l2 = llHorizontal.height
        var l4 = llVertical.height
        var l1 = llHorizontal.y
        var l3 = llVertical.y
        var param: ViewGroup.MarginLayoutParams = llVertical.layoutParams as ViewGroup.MarginLayoutParams
        var l5 = llVertical.x + param.leftMargin
        var l6 = llVertical.width
        horizontalAnim = ObjectAnimator.ofFloat(llHorizontal, "y", -l2 - l1, l1)
        horizontalAnimBack = ObjectAnimator.ofFloat(llHorizontal, "y", l1, -l2 - l1)
        verticalAnim = ObjectAnimator.ofFloat(llVertical, "y", l3 + l4, l3)
        verticalAnimBack = ObjectAnimator.ofFloat(llVertical, "y", l3, l3 + l4)
        rightAnim = ObjectAnimator.ofFloat(llClassify, "x", l6 + l5, l5)
        leftAnim = ObjectAnimator.ofFloat(llVertical, "x", l5, l5 - l6)
        rightAnimBack = ObjectAnimator.ofFloat(llClassify, "x", l5, l6 + l5)
        leftAnimBack = ObjectAnimator.ofFloat(llVertical, "x", l5 - l6, l5)
        horizontalAnim?.duration = 400
        verticalAnim?.duration = 500
        horizontalAnimBack?.duration = 200
        verticalAnimBack?.duration = 200
        rightAnim?.duration = 400
        leftAnim?.duration = 400
        rightAnimBack?.duration = 400
        leftAnimBack?.duration = 400
        rightAnim?.interpolator = interpolator
        leftAnim?.interpolator = interpolator
        rightAnimBack?.interpolator = interpolator
        leftAnimBack?.interpolator = interpolator
        verticalAnim?.interpolator = interpolator
        horizontalAnim?.interpolator = interpolator
        verticalAnimBack?.interpolator = interpolator
        horizontalAnimBack?.interpolator = interpolator
        verticalAnimBack?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                finish()
                overridePendingTransition(0, 0)

            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
        horizontalAnim?.startDelay = 100
    }

    @OnClick(R.id.ib_back, R.id.ib_classify, R.id.tv_classify)
    fun back(view: View) {
        when (view.id) {
            R.id.ib_back -> {
                horizontalAnimBack?.start()
                verticalAnimBack?.start()
            }
            R.id.ib_classify -> {
                leftAnimBack?.start()
                rightAnimBack?.start()
                classifyOpened = false
            }
            R.id.tv_classify -> {
                leftAnim?.start()
                rightAnim?.start()
                llClassify.visibility = View.VISIBLE
                classifyOpened = true
            }
        }

    }

    override fun onBackPressed() {
        if (classifyOpened) {
            ibClassify.performClick()
        } else {
            ibBack.performClick()
        }
    }

    @Subscribe
    fun jumpToCategory(categoryEvent: JumpCategoryEvent) {
        startActivity(CategoryActivity.getIntent(this, categoryEvent.id, categoryEvent.title))
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}