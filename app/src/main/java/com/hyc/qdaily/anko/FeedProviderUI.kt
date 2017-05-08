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
 * Created by ray on 17/5/1.
 */
class FeedProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        linearLayout {
            lparams(height = dip(130), width = matchParent)
            backgroundColor = getResourceColor(R.color.content_bg)
            orientation = LinearLayout.HORIZONTAL
            verticalLayout {
                textView {
                    ellipsize = TextUtils.TruncateAt.END
                    textColor = getResourceColor(R.color.text_color_normal)
                    textSize = 16f
                    bold()
                    id = R.id.tv_description

                }.lparams(height = 0, weight = 1f, width = matchParent) {
                    margin = dip(18)
                }

                linearLayout {
                    textView {
                        textColor = getResourceColor(R.color.text_small)
                        id = R.id.tv_cat_title
                        textSize = 12f
                    }.lparams(width = wrapContent, height = wrapContent)

                    textView {
                        compoundDrawablePadding = dip(2)
                        gravity = Gravity.CENTER_VERTICAL
                        textColor = getResourceColor(R.color.text_small)
                        textSize = 12f
                        id = R.id.tv_comment_count
                        drawableLeft(R.drawable.icon_comment)
                    }.lparams(height = wrapContent, width = wrapContent) {
                        leftMargin = dip(4)
                    }
                    textView {
                        compoundDrawablePadding = dip(2)
                        gravity = Gravity.CENTER_VERTICAL
                        textColor = getResourceColor(R.color.text_small)
                        textSize = 12f
                        id = R.id.tv_praise_count
                        drawableLeft(R.drawable.icon_praise)
                    }.lparams(height = wrapContent, width = wrapContent) {
                        leftMargin = dip(4)
                    }
                    textView {
                        textSize = 12f
                        textColor = getResourceColor(R.color.text_small)
                        id = R.id.tv_time
                    }.lparams(width = wrapContent, height = wrapContent) {
                        leftMargin = dip(4)
                    }
                    leftPadding = dip(18)
                    bottomPadding = dip(18)
                }.lparams(width = wrapContent, height = wrapContent)
            }.lparams(width = 0, height = matchParent, weight = 4f)

            simpleDraweeView {
                id = R.id.sdv_img
            }.lparams(width = 0, height = matchParent, weight = 3f)
        }

    }.view

    override fun unbind(t: Context) {
    }

}