package com.hyc.qdaily.anko

import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.*
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import com.hyc.qdaily.util.getResourceColorList
import com.hyc.qdaily.util.getString
import com.hyc.qdaily.view.ScrollAwareFABBehavior
import com.hyc.qdaily.view.activity.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.support.v4.viewPager

/**
 * Created by ray on 17/4/25.
 */
class MainActivityUI : ViewBinder<MainActivity> {
    override fun bind(t: MainActivity): View = t.UI {
        coordinatorLayout {
            id = R.id.activity_main
            lparams(width = matchParent, height = matchParent)
            backgroundColor = getResourceColor(R.color.content_bg)
            appBarLayout {
                id = R.id.appbar
                lparams(width = matchParent, height = wrapContent)
                backgroundColor = getResourceColor(R.color.tab_bg)
                relativeLayout {
                    id = R.id.rl_indicator

                    linearLayout {
                        id = R.id.ll_bar
                        gravity = Gravity.CENTER
                        orientation = LinearLayout.HORIZONTAL
                        lparams(width = matchParent, height = matchParent)
                        space {
                            lparams(width = 0, height = wrapContent, weight = 1f)
                        }
                        textView {
                            lparams(width = wrapContent, height = wrapContent)
                            id = R.id.tv_news
                            textSize = 16f
                            textColorList = getResourceColorList(R.drawable.tab_text_selector)
                            text = getString(R.string.news)
                        }
                        space {
                            lparams(width = 0, height = wrapContent, weight = 2f)
                        }
                        textView {
                            lparams(width = wrapContent, height = wrapContent)
                            id = R.id.tv_lab
                            textSize = 16f
                            textColorList = getResourceColorList(R.drawable.tab_text_selector)
                            text = getString(R.string.lab)
                        }
                        space {
                            lparams(width = 0, height = wrapContent, weight = 1f)
                        }
                    }
                    view {
                        id = R.id.indicator
                        backgroundResource = R.drawable.pager_indicator
                    }.lparams(width = dip(45), height = dip(3)) {
                        alignParentBottom()
                    }
                }.lparams(width = matchParent, height = dip(42)) {
                    scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS or SCROLL_FLAG_SNAP
                }
            }
            viewPager {
                id = R.id.vp_target
            }.lparams(width = matchParent, height = wrapContent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
            floatingActionButton {
                id = R.id.fab_main
                imageResource = R.drawable.fab_selector
                compatElevation = 0f
                backgroundTintList = getResourceColorList(android.R.color.transparent)
                rippleColor = getResourceColor(android.R.color.transparent)

            }.lparams {
                width = dip(90)
                height = dip(90)
                behavior = ScrollAwareFABBehavior()
                gravity = Gravity.BOTTOM or Gravity.LEFT
            }
        }
    }.view

    override fun unbind(t: MainActivity) {
    }

}