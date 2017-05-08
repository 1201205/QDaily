package com.hyc.qdaily.anko

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.UI
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by ray on 17/4/27.
 */
class LabFragmentUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        pullToRefreshView {
            backgroundColor = getResourceColor(R.color.main_bg)
            recyclerView {
                id = R.id.target
                lparams(width = matchParent, height = matchParent)
            }
        }.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }.view

    override fun unbind(t: Context) {
    }

}