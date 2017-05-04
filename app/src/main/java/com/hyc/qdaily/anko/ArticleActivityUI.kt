package com.hyc.qdaily.anko

import android.content.Context
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/5/4.
 */
class ArticleActivityUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            backgroundColor = getResourceColor(R.color.content_bg)
            webView {
                id = R.id.wb_article
            }.lparams(width = matchParent, height = matchParent)
        }
    }.view

    override fun unbind(t: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}