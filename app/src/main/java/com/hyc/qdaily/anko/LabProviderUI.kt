package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/5/1.
 */
class LabProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            backgroundColor = getResourceColor(R.color.content_bg)
            relativeLayout {
                simpleDraweeView { id = R.id.sdv_img }.lparams(height = matchParent, width = matchParent)
                simpleDraweeView {
                    id = R.id.sdv_icon
                }.lparams(width = dip(30), height = dip(30)) {
                    alignParentBottom()
                    leftMargin = dip(18)
                    bottomMargin = dip(18)
                }
                linearLayout {
                    id = R.id.ll_count
                    gravity = Gravity.CENTER_HORIZONTAL
                    backgroundResource = R.drawable.icon_lab_vot_join
                    textView {
                        textSize = 14f
                        id = R.id.tv_count
                        textColor = getResourceColor(R.color.indicator_select)
                    }.lparams(height = wrapContent, width = wrapContent) {
                        topMargin = dip(3)
                    }
                }.lparams(width = dip(57), height = dip(43)) {
                    alignParentRight()
                    topMargin = dip(18)
                    rightMargin = dip(18)
                }
            }.lparams(width = matchParent, height = dip(180))
            textView {
                bold()
                textSize = 15f
                textColor = getResourceColor(R.color.text_color_normal)
                id = R.id.tv_title
            }.lparams(height = wrapContent, width = wrapContent) {
                rightMargin = dip(18)
                topMargin = dip(15)
                leftMargin = dip(18)
            }
            textView {
                textSize = 13f
                textColor = getResourceColor(R.color.text_small)
                id = R.id.tv_des
            }.lparams(height = wrapContent, width = wrapContent) {
                rightMargin = dip(18)
                topMargin = dip(5)
                leftMargin = dip(18)
                bottomMargin = dip(15)
            }
        }
    }.view

    override fun unbind(t: Context) {
    }

}