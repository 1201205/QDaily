package com.hyc.qdaily.anko

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/4/28.
 */
class BookProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            lparams(width = dip(180), height = wrapContent)
            backgroundResource = R.drawable.recycler_bg
            simpleDraweeView {
                id = R.id.sdv_img
            }.lparams(width = dip(180), height = dip(180))
            textView {
                maxLines = 2
                minLines = 2
                id = R.id.tv_title
                textColor = getResourceColor(R.color.text_color_normal)
                textSize = 15f
                ellipsize = TextUtils.TruncateAt.END
                bold()
            }.lparams(width = wrapContent, height = wrapContent) {
                topMargin = dip(15)
                leftMargin = dip(10)
                rightMargin = dip(10)
            }

            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                textView {
                    compoundDrawablePadding = dip(2)
                    textColor = getResourceColor(R.color.text_small)
                    textSize = 12f
                    id = R.id.tv_comment_count
                    drawableLeft(R.drawable.icon_comment)

                }.lparams(width = wrapContent, height = wrapContent) {
                    leftMargin = dip(4)
                    gravity = Gravity.CENTER_VERTICAL
                }

                textView {
                    compoundDrawablePadding = dip(2)
                    drawableLeft(R.drawable.icon_praise)
                    id = R.id.tv_praise_count
                    textSize = 12f
                    textColor = getResourceColor(R.color.text_small)

                }.lparams(width = wrapContent, height = wrapContent) {
                    leftMargin = dip(8)
                    gravity = Gravity.CENTER_VERTICAL
                }
                leftPadding = dip(10)
                bottomPadding = dip(10)
                gravity = Gravity.CENTER_HORIZONTAL
            }.lparams(width = wrapContent, height = wrapContent)
        }
    }.view

    override fun unbind(t: Context) {
    }

}