package com.hyc.qdaily.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView
import butterknife.OnClick
import com.hyc.qdaily.R
import com.hyc.qdaily.anko.MainActivityUI
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.presenter.VoidPresenter
import com.hyc.qdaily.view.adpter.FragmentPagerAdapter
import com.hyc.qdaily.view.fragment.LabFragment
import com.hyc.qdaily.view.fragment.MainFragment


class MainActivity : BaseActivity<VoidPresenter>() {
    override fun generateView(): View = MainActivityUI().bind(this)

    lateinit var vpTarget: ViewPager
    lateinit var indicator: View
    lateinit var tvNews: TextView
    lateinit var tvLab: TextView
    lateinit var fabMain: FloatingActionButton
    private var preIndex: Int = 0
    private var mIndicatorY: IntArray = IntArray(2)
    private var mIndicatorScroll: Int = 0
    override fun isSupportSwipeBack(): Boolean {
        return false
    }

    override fun handleIntent() {
    }

    override val layoutID = R.layout.activity_main2

    override fun initPresenterAndData() {
    }

    override fun initView() {

//        vpTarget = find(R.id.vp_target)
//        indicator = find(R.id.indicator)
//        tvNews = find(R.id.tv_news)
//        tvLab = find(R.id.tv_lab)
//        fabMain = find(R.id.fab_main)
//
//        checkSDPermission()
        if (Build.VERSION.SDK_INT >= 23) {
            checkSDPermission()
        }
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
                        tvNews.isSelected = true
                        return true
                    }
                })
        var adapter = FragmentPagerAdapter(supportFragmentManager)
        adapter.add(MainFragment())
        adapter.add(LabFragment())
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
    }

    private fun checkSDPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return
        }
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
        ActivityCompat.requestPermissions(this, permissions, 1)
    }

    @OnClick(R.id.fab_main)
    fun onClick() {
        startActivity(Intent(this, MenuActivity::class.java))
        overridePendingTransition(0, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onNetError() {
    }
}
