package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/5/4.
 */
class GuessActivityUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            lparams(width = matchParent, height = matchParent) {
                leftMargin = dip(18)
                rightMargin = dip(18)
                gravity = Gravity.CENTER_HORIZONTAL
            }
            backgroundColor = getResourceColor(R.color.content_bg)
            linearLayout {
                id = R.id.ll_title
                view {
                    backgroundColor = getResourceColor(R.color.guess_divider)
                }.lparams(width = 0, weight = 1f, height = 1)
                relativeLayout {
                    backgroundResource = R.drawable.indicator_bg
                    view {
                        backgroundColor = getResourceColor(R.color.guess_wrong_bg)
                        rotation = -45f
                    }.lparams(width = dip(36), height = dip(1)) {
                        centerInParent()
                    }
                    textView {
                        id = R.id.tv_index
                        textSize = 15f
                    }.lparams(width = wrapContent, height = wrapContent) {
                        leftMargin = dip(8)
                        topMargin = dip(10)
                    }

                    textView {
                        id = R.id.tv_count
                        textSize = 13f
                    }.lparams(width = wrapContent, height = wrapContent) {
                        bottomMargin = dip(8)
                        rightMargin = dip(10)
                        alignParentRight()
                        alignParentBottom()
                    }
                }.lparams(width = dip(44), height = dip(44)) {
                    rightMargin = dip(10)
                    leftMargin = dip(10)
                }
                view {
                    backgroundColor = getResourceColor(R.color.guess_divider)
                }.lparams(width = 0, weight = 1f, height = 1)
            }.lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(30)
                gravity = Gravity.CENTER_VERTICAL
            }
            textView {
                id = R.id.tv_title
                textSize = 16f
            }.lparams(width = wrapContent, height = wrapContent) {
                topMargin = dip(20)
                rightMargin = dip(10)
                gravity = Gravity.CENTER_HORIZONTAL
            }

            linearLayout {
                id = R.id.ll_top
                verticalLayout {
                    id = R.id.ll1
                    simpleDraweeView {
                        id = R.id.sdv_1
                    }.lparams(height = dip(140), width = matchParent) {
                        margin = dip(5)
                    }
                    textView(R.style.guess_text) {
                        id = R.id.tv_name1
                    }
                }.lparams(width = 0, height = wrapContent, weight = 1f) {
                    gravity = Gravity.CENTER
                    rightMargin = dip(5)
                }
                verticalLayout {
                    id = R.id.ll2
                    simpleDraweeView {
                        id = R.id.sdv_2
                    }.lparams(height = dip(140), width = matchParent) {
                        margin = dip(5)
                    }
                    textView(R.style.guess_text) {
                        id = R.id.tv_name2
                    }
                }.lparams(width = 0, height = wrapContent, weight = 1f) {
                    gravity = Gravity.CENTER
                    leftMargin = dip(5)
                }
            }.lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(30)
            }

            linearLayout {
                verticalLayout {
                    id = R.id.ll3
                    simpleDraweeView {
                        id = R.id.sdv_3
                    }.lparams(height = dip(140), width = matchParent) {
                        margin = dip(5)
                    }
                    textView(R.style.guess_text) {
                        id = R.id.tv_name3
                    }
                }.lparams(width = 0, height = wrapContent, weight = 1f) {
                    gravity = Gravity.CENTER
                    rightMargin = dip(5)
                }
                verticalLayout {
                    id = R.id.ll4
                    simpleDraweeView {
                        id = R.id.sdv_4
                    }.lparams(height = dip(140), width = matchParent) {
                        margin = dip(5)
                    }
                    textView(R.style.guess_text) {
                        id = R.id.tv_name4
                    }
                }.lparams(width = 0, height = wrapContent, weight = 1f) {
                    gravity = Gravity.CENTER
                    leftMargin = dip(5)
                }
            }.lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(30)
            }
        }
    }.view

    override fun unbind(t: Context) {
    }

}