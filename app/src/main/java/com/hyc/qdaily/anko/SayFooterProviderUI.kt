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
class SayFooterProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {

        linearLayout {
            id = R.id.rl_content
            lparams(width = matchParent, height = wrapContent) {
                gravity = Gravity.CENTER
            }
            view {
                backgroundColor = getResourceColor(R.color.text_small)
            }.lparams(width = dip(30), height = dip(1)) {
                rightMargin = dip(10)
            }
            textView {
                backgroundColor = getResourceColor(R.color.text_small)
                text = "没有更多了"
            }.lparams(width = wrapContent, height = wrapContent)
            view {
                backgroundColor = getResourceColor(R.color.text_small)
            }.lparams(width = dip(30), height = dip(1)) {
                leftMargin = dip(10)
            }
        }
    }.view

    override fun unbind(t: Context) {
    }

}