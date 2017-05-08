package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import com.hyc.qdaily.util.getString
import org.jetbrains.anko.*

/**
 * Created by ray on 17/5/1.
 */
class HeadLineProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            lparams(width = matchParent, height = wrapContent) {
                leftMargin = dip(5)
                rightMargin = dip(5)
            }
            backgroundResource = R.drawable.headline_bg
            linearLayout {
                skinDateView {
                    borderColor = getResourceColor((R.color.border_color))
                    bottomFontColor = getResourceColor((R.color.day_font))
                    topBgColor = getResourceColor((R.color.week_bg))
                    topFontColor = getResourceColor((R.color.week_font))
                    bottomBgColor = getResourceColor((R.color.day_bg))
                }.lparams(width = dip(35), height = dip(35))
                textView {
                    textSize = 15f
                    bold()
                    text = getString(R.string.head_line)
                    textColor = getResourceColor(R.color.text_color_normal)
                }.lparams(width = wrapContent, height = wrapContent) {
                    leftMargin = dip(8)
                    gravity = Gravity.CENTER_VERTICAL
                }
            }.lparams(height = wrapContent, width = wrapContent) {
                leftMargin = dip(6)
                topMargin = dip(6)
            }

            linearLayout {
                id = R.id.ll_line1
                view {
                    backgroundResource = R.drawable.indicator
                }.lparams(width = dip(8), height = dip(8)) {
                    leftMargin = dip(18)
                    rightMargin = dip(18)
                    gravity = Gravity.CENTER_VERTICAL
                }
                textView {
                    textSize = 15f
                    maxLines = 3
                    minLines = 3
                    id = R.id.tv_line1
                    textColor = getResourceColor(R.color.text_color_normal)
                }.lparams(width = wrapContent, height = wrapContent) {
                    rightMargin = dip(18)
                }
            }.lparams(height = matchParent, width = matchParent) {
                bottomMargin = dip(5)
            }
            linearLayout {
                id = R.id.ll_line2
                view {
                    backgroundResource = R.drawable.indicator
                }.lparams(width = dip(8), height = dip(8)) {
                    leftMargin = dip(18)
                    rightMargin = dip(18)
                    gravity = Gravity.CENTER_VERTICAL
                }
                textView {
                    textSize = 15f
                    maxLines = 3
                    minLines = 3
                    id = R.id.tv_line2
                    textColor = getResourceColor(R.color.text_color_normal)
                }.lparams(width = wrapContent, height = wrapContent) {
                    rightMargin = dip(18)
                }
            }.lparams(height = matchParent, width = matchParent) {
                bottomMargin = dip(5)
            }
            linearLayout {
                id = R.id.ll_line3
                view {
                    backgroundResource = R.drawable.indicator
                }.lparams(width = dip(8), height = dip(8)) {
                    leftMargin = dip(18)
                    rightMargin = dip(18)
                    gravity = Gravity.CENTER_VERTICAL
                }
                textView {
                    textSize = 15f
                    maxLines = 3
                    minLines = 3
                    id = R.id.tv_line3
                    textColor = getResourceColor(R.color.text_color_normal)
                }.lparams(width = wrapContent, height = wrapContent) {
                    rightMargin = dip(18)
                }
            }.lparams(height = matchParent, width = matchParent) {
                bottomMargin = dip(5)
            }

            linearLayout {
                space { }.lparams(width = 0, height = wrapContent, weight = 1f)
                textView {
                    id = R.id.tv_detail
                    textColor = getResourceColor(R.color.text_small)
                    compoundDrawablePadding = dip(3)
                    text = getString(R.string.show_detail)
                    drawableRight(R.drawable.icon_composite_arrow)
                }.lparams(width = wrapContent, height = wrapContent) {
                    rightMargin = dip(18)
                }
            }.lparams(width = matchParent, height = wrapContent) {
                bottomMargin = dip(5)
            }
        }
    }.view

    override fun unbind(t: Context) {
    }

}