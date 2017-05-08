package com.hyc.qdaily.anko

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/5/3.
 */
class VerticalProviderUI : ViewBinder<View> {
    override fun bind(c: View): View = c.context.UI {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            backgroundColor = getResourceColor(R.color.content_bg)
            lparams(width = c.measuredWidth, height = wrapContent)
            simpleDraweeView {
                id = R.id.sdv_img
            }.lparams(width = matchParent, height = dip(180))
            textView {
                id = R.id.tv_title
                bold()
                textSize = 15f
                textColor = getResourceColor(R.color.text_color_normal)
            }.lparams(width = wrapContent, height = wrapContent) {
                rightMargin = dip(18)
                topMargin = dip(15)
                leftMargin = dip(18)
            }
            textView {
                id = R.id.tv_des
                textColor = getResourceColor(R.color.text_small)
            }.lparams(width = wrapContent, height = wrapContent) {
                rightMargin = dip(18)
                topMargin = dip(5)
                leftMargin = dip(18)
                bottomMargin = dip(5)
            }
            linearLayout {
                textView {
                    id = R.id.tv_cat_title
                    textSize = 12f
                    textColor = getResourceColor(R.color.text_small)
                }.lparams(width = wrapContent, height = wrapContent)

                textView {
                    id = R.id.tv_praise_count
                    textSize = 12f
                    compoundDrawablePadding = dip(2)
                    drawableLeft(R.drawable.icon_praise)
                    textColor = getResourceColor(R.color.text_small)
                }.lparams(width = wrapContent, height = wrapContent) {
                    leftMargin = dip(4)
                    gravity = Gravity.CENTER_VERTICAL
                }
                textView {
                    id = R.id.tv_time
                    textSize = 12f
                    textColor = getResourceColor(R.color.text_small)
                }.lparams(width = wrapContent, height = wrapContent) {
                    leftMargin = dip(4)
                }
                leftPadding = dip(18)
                rightPadding = dip(18)
                bottomPadding = dip(18)
            }.lparams(width = wrapContent, height = wrapContent)
        }
    }.view

    override fun unbind(t: View) {
    }

}