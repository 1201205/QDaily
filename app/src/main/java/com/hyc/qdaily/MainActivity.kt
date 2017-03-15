package com.hyc.qdaily

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView
import butterknife.BindView
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.presenter.HomePresenter
import com.hyc.qdaily.util.StatusBarUtil
import com.hyc.qdaily.view.adpter.FragmentPagerAdapter
import com.hyc.qdaily.view.fragment.MainFragment

class MainActivity : BaseActivity<HomePresenter>() {
    @BindView(R.id.vp_target)
    lateinit var vpTarget: ViewPager
    @BindView(R.id.indicator)
    lateinit var indicator: View
    @BindView(R.id.tv_news)
    lateinit var tvNews: TextView
    @BindView(R.id.tv_lab)
    lateinit var tvLab: TextView
    var preIndex: Int = 0
    var mIndicatorY: IntArray = IntArray(2)
    var mIndicatorScroll: Int = 0
    override fun isSupportSwipeBack(): Boolean {
        return false
    }

    override fun handleIntent() {
    }

    override val layoutID = R.layout.activity_main2

    override fun initPresenterAndData() {
    }

    override fun initView() {
        StatusBarUtil.StatusBarLightMode(this)
        indicator.viewTreeObserver
                .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        indicator.viewTreeObserver.removeOnPreDrawListener(this)
                         val width = indicator.width
                        val textWidth = tvNews.width
                        val location = IntArray(2)
                        tvNews.getLocationInWindow(location)
                        mIndicatorY[0] = location[0] - (width - textWidth) / 2
                        tvLab.getLocationInWindow(location)
                        mIndicatorY[1] = location[0] - (width - textWidth) / 2
                        indicator.x = mIndicatorY[0].toFloat()
                        mIndicatorScroll = mIndicatorY[1] - mIndicatorY[0]
                        tvNews.isSelected=true
                        return true
                    }
                })
        var adapter = FragmentPagerAdapter(supportFragmentManager)
        adapter.add(MainFragment())
        adapter.add(MainFragment())
        vpTarget.adapter = adapter
        vpTarget.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (preIndex > position) {
                    indicator.x = (positionOffset - 1) * mIndicatorScroll + (mIndicatorScroll * preIndex).toFloat() + mIndicatorY[0]
                } else if (preIndex <= position) {
                    indicator.x = (positionOffset * mIndicatorScroll + (mIndicatorScroll * preIndex).toFloat() + mIndicatorY[0].toFloat())
                }
            }

            override fun onPageSelected(position: Int) {
                preIndex = position
                when (position) {
                    0 -> {
                        tvNews.isSelected = true
                        tvLab.isSelected = false
                    }
                    else -> {
                        tvNews.isSelected = false
                        tvLab.isSelected = true
                    }
                }
            }

        })
        vpTarget.addOnPageChangeListener(ViewPager.SimpleOnPageChangeListener())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
