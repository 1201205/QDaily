package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/5/3.
 */
class VoteProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        linearLayout {
            backgroundColor = getResourceColor(R.color.vote_bg)
            lparams(width = matchParent, height = wrapContent) {
                gravity = Gravity.CENTER_VERTICAL
                leftMargin = dip(18)
                rightMargin = dip(18)
                topMargin = dip(10)
                bottomMargin = dip(10)
            }
            simpleDraweeView {
                id = R.id.sdv_icon
            }.lparams(width = dip(80), height = dip(80))
            textView {
                id = R.id.tv_des
                textSize = 14f
                textColor = getResourceColor(R.color.text_color_normal)
            }.lparams(width = wrapContent, height = wrapContent, weight = 1f) {
                leftMargin = dip(10)
                rightMargin = dip(10)
            }
            imageView {
                id = R.id.iv_vote
            }.lparams(width = wrapContent, height = wrapContent) {
                rightMargin = dip(10)
            }
        }
    }.view

    override fun unbind(t: Context) {
    }


}