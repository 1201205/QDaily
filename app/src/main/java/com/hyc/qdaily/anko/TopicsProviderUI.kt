package com.hyc.qdaily.anko

import android.content.Context
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by ray on 17/5/3.
 */
class TopicsProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            backgroundColor = getResourceColor(R.color.content_bg)
            lparams(width = matchParent, height = wrapContent) {
                topPadding = dip(5)
                bottomPadding = dip(5)
            }
            recyclerView {
                id = R.id.rv_topic
            }.lparams(width = matchParent, height = wrapContent)
        }
    }.view

    override fun unbind(t: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}