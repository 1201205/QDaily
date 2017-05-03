package com.hyc.qdaily.anko

import android.content.Context
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/5/3.
 */
class SubmitProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        relativeLayout {
            backgroundResource = R.drawable.submit_bg
            lparams(width = matchParent, height = wrapContent) {
                leftMargin = dip(18)
                rightMargin = dip(18)
                bottomMargin = dip(18)
                topMargin = dip(10)
            }
            imageView {
                imageResource = R.drawable.icon_vote_tip
            }.lparams(width = dip(40), height = dip(40)) {
                leftMargin = dip(18)
                centerVertically()
            }
            textView {
                text = "投 票"
                textSize = 22f
                textColor = getResourceColor(R.color.content_bg)
            }.lparams(width = wrapContent, height = wrapContent) {
                centerInParent()
            }
        }
    }.view

    override fun unbind(t: Context) {
    }

}