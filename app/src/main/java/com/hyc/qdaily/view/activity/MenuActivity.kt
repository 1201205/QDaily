package com.hyc.qdaily.view.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.OnClick
import com.hyc.qdaily.R
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.presenter.VoidPresenter

/**
 * Created by ray on 17/3/22.
 */
class MenuActivity : BaseActivity<VoidPresenter>() {
    @BindView(R.id.ll_horizontal)
    lateinit var llHorizontal: LinearLayout
    @BindView(R.id.ll_vertical)
    lateinit var llVertical: LinearLayout
    var horizontalAnim: Animator? = null
    var verticalAnim: Animator? = null
    var horizontalAnimBack: Animator? = null
    var verticalAnimBack: Animator? = null
    var interpolator: AccelerateDecelerateInterpolator = AccelerateDecelerateInterpolator()

    override val layoutID = R.layout.activity_menu
    override fun onNetError() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
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
                    var l2 = llHorizontal.height
                    var l4 = llVertical.height
                    if (l2 == 0 || l4 == 0) {
                        return true
                    }
                    var l1 = llHorizontal.y
                    var l3 = llVertical.y
                    llHorizontal.viewTreeObserver.removeOnPreDrawListener(this)
                    horizontalAnim = ObjectAnimator.ofFloat(llHorizontal, "y", -l2 - l1, l1 + 30, l1)
                    horizontalAnimBack = ObjectAnimator.ofFloat(llHorizontal, "y", l1, -l2 - l1)
                    verticalAnim = ObjectAnimator.ofFloat(llVertical, "y", l3 + l4, l3 - 30, l3)
                    verticalAnimBack = ObjectAnimator.ofFloat(llVertical, "y", l3, l3 + l4)
                    horizontalAnim?.duration = 400
                    verticalAnim?.duration = 400
                    horizontalAnimBack?.duration = 400
                    verticalAnimBack?.duration = 400
                    verticalAnim?.interpolator = interpolator
                    horizontalAnim?.interpolator = interpolator
                    verticalAnimBack?.interpolator = interpolator
                    horizontalAnimBack?.interpolator = interpolator
                    verticalAnimBack?.addListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {
                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            finish()
                            overridePendingTransition(0,0)

                        }

                        override fun onAnimationCancel(animation: Animator?) {
                        }

                        override fun onAnimationStart(animation: Animator?) {
                        }

                    })
                    horizontalAnim?.start()
                    verticalAnim?.start()
                }
                return true
            }
        }
        )

    }
    @OnClick(R.id.ib_back)
    fun back() {
        horizontalAnimBack?.start()
        verticalAnimBack?.start()
    }

    override fun onBackPressed() {
        back()
    }
}