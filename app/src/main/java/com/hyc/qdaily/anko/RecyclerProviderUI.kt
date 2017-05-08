package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by ray on 17/5/3.
 */
class RecyclerProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {

        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            backgroundColor = getResourceColor(R.color.content_bg)
            linearLayout {
                simpleDraweeView {
                    roundAsCircle()
                    id = R.id.sdv_icon
                }.lparams(width = dip(25), height = dip(25))
                textView {
                    id = R.id.tv_name
                }.lparams(height = wrapContent, width = wrapContent) {
                    leftMargin = dip(10)
                }
                space {}.lparams(height = wrapContent, width = 0, weight = 1f)
                imageView {
                    id = R.id.iv_share
                    imageResource = R.drawable.icon_share
                }.lparams(width = wrapContent, height = wrapContent)
                leftPadding = dip(18)
                rightPadding = dip(18)
                gravity = Gravity.CENTER_VERTICAL
            }.lparams(width = matchParent, height = dip(50))
            view {
                backgroundColor = getResourceColor(R.color.divider_color)
            }.lparams(width = matchParent, height = 1) {
                leftMargin = dip(18)
                rightMargin = dip(18)
            }
            recyclerView {
                id = R.id.rv_language
            }.lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(6)
                bottomMargin = dip(15)
            }
        }
    }.view

    override fun unbind(t: Context) {
    }

}