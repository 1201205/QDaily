package com.hyc.qdaily.anko

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by ray on 17/5/4.
 */
class SayActivityUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            backgroundColor = getResourceColor(R.color.content_bg)
            recyclerView {
                id = R.id.rv_target
            }.lparams(width = matchParent, height = 0, weight = 1f)
            include<RelativeLayout>(R.layout.layout_bottom_bar)
        }
    }.view

    override fun unbind(t: Context) {
    }

}