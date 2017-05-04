package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by ray on 17/5/4.
 */
class CategoryActivityUI : ViewBinder<Context> {
    override fun unbind(t: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            id = R.id.activity_main
            backgroundColor = getResourceColor(R.color.main_bg)
            linearLayout {
                id = R.id.ll_title
                gravity = Gravity.CENTER
                backgroundColor = getResourceColor(R.color.content_bg)
                imageView {
                    imageResource = R.drawable.nav_back
                    id = R.id.iv_back
                    padding = dip(10)
                }.lparams(width = wrapContent, height = wrapContent)
                textView {
                    id = R.id.tv_title
                    gravity = Gravity.CENTER
                    textSize = 16f
                    textColor = getResourceColor(R.color.text_color_normal)
                }.lparams(width = wrapContent, height = wrapContent, weight = 1f)
            }.lparams(width = matchParent, height = wrapContent)
            pullToRefreshView {
                recyclerView {
                    id = R.id.target
                }.lparams(width = matchParent, height = matchParent)
            }.lparams(width = matchParent, height = matchParent)
        }
    }.view

}