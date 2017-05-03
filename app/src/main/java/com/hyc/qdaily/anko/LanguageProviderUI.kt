package com.hyc.qdaily.anko

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/5/1.
 */
class LanguageProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            backgroundResource = R.drawable.recycler_bg
            lparams(width = dip(240), height = wrapContent)
            simpleDraweeView {
                id = R.id.sdv_img
            }.lparams(width = dip(240), height = dip(120))
            textView {
                bold()
                maxLines = 2
                minLines = 2
                ellipsize = TextUtils.TruncateAt.END
                id = R.id.tv_title
                textSize = 15f
                textColor = getResourceColor(R.color.text_color_normal)
            }.lparams(height = wrapContent, width = wrapContent) {
                rightMargin = dip(10)
                leftMargin = dip(10)
                topMargin = dip(15)
            }

            textView {
                bold()
                maxLines = 2
                minLines = 2
                ellipsize = TextUtils.TruncateAt.END
                id = R.id.tv_des
                textSize = 13f
                textColor = getResourceColor(R.color.text_small)
            }.lparams(height = wrapContent, width = wrapContent) {
                rightMargin = dip(10)
                leftMargin = dip(10)
                topMargin = dip(5)
                bottomMargin = dip(15)
            }

            linearLayout {

                textView {
                    id = R.id.tv_comment_count
                    textSize = 12f
                    compoundDrawablePadding = dip(2)
                    drawableLeft(R.drawable.icon_comment)
                    textColor = getResourceColor(R.color.text_small)
                }.lparams(width = wrapContent, height = wrapContent) {
                    leftMargin = dip(4)
                    gravity = Gravity.CENTER_VERTICAL
                }

                textView {
                    id = R.id.tv_praise_count
                    textSize = 12f
                    compoundDrawablePadding = dip(2)
                    drawableLeft(R.drawable.icon_praise)
                    textColor = getResourceColor(R.color.text_small)
                }.lparams(width = wrapContent, height = wrapContent) {
                    leftMargin = dip(4)
                    gravity = Gravity.CENTER_VERTICAL
                }
                textView {
                    id = R.id.tv_time
                    textSize = 12f
                    textColor = getResourceColor(R.color.text_small)
                }.lparams(width = wrapContent, height = wrapContent) {
                    leftMargin = dip(4)
                }
                leftPadding = dip(10)
                bottomPadding = dip(10)
            }.lparams(width = wrapContent, height = wrapContent)
        }
    }.view

    override fun unbind(t: Context) {
    }

}