package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/4/29.
 */
class ClassifyProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(10)
                bottomMargin = dip(10)
                gravity = Gravity.CENTER_VERTICAL
            }
            simpleDraweeView {
                id = R.id.sdv_icon
                roundAsCircle()
            }.lparams(width = dip(40), height = dip(40))
            textView {
                id = R.id.tv_name
                textColor = getResourceColor(R.color.text_color_normal)
            }.lparams(height = wrapContent, width = wrapContent) {
                leftMargin = dip(15)
            }
        }

    }.view

    override fun unbind(t: Context) {
    }

}