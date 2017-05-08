package com.hyc.qdaily.anko

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/5/3.
 */
class SayProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        relativeLayout {
            id = R.id.rl_content
            lparams(height = wrapContent, width = wrapContent) {
                margin = dip(5)
            }
            minimumHeight = dip(150)
            simpleDraweeView {
                id = R.id.sdv_head
                roundAsCircle()
            }.lparams(width = dip(30), height = dip(30)) {
                topMargin = dip(10)
                leftMargin = dip(10)
            }
            textView {
                id = R.id.tv_name
                textSize = 12f
                textColor = getResourceColor(R.color.banner_text)
            }.lparams(height = wrapContent, width = wrapContent) {
                leftMargin = dip(7)
                topMargin = dip(18)
                rightOf(R.id.sdv_head)
            }
            expandableTextView {
                ellipsize = TextUtils.TruncateAt.END
                maxLines = 4
                textColor = getResourceColor(R.color.banner_text)
                id = R.id.etv_content
                setAnimationDuration(200)
            }.lparams(width = wrapContent, height = wrapContent) {
                topMargin = dip(10)
                rightMargin = dip(10)
                leftMargin = dip(10)
                bottomMargin = dip(30)
                gravity = Gravity.CENTER
                centerHorizontally()
                below(R.id.sdv_head)
            }
            textView {
                textColor = getResourceColor(R.color.banner_text)
                textSize = 12f
            }.lparams(width = matchParent, height = wrapContent) {
                rightMargin = dip(10)
                leftMargin = dip(10)
                topMargin = dip(10)
                bottomMargin = dip(30)
                centerHorizontally()
                below(R.id.sdv_head)
            }
            textView {
                compoundDrawablePadding = dip(6)
                textColor = getResourceColor(R.color.banner_text)
                drawableLeft(R.drawable.icon_mobc_comment_unlike)
                id = R.id.tv_praise_count
            }.lparams(width = wrapContent, height = wrapContent) {
                padding = dip(10)
                alignParentBottom()
            }
            imageView {
                imageResource = R.drawable.icon_nav_share_night
                id = R.id.iv_share
            }.lparams(width = wrapContent, height = wrapContent) {
                padding = dip(10)
                alignParentBottom()
                alignParentRight()
            }
        }
    }.view

    override fun unbind(t: Context) {
    }

}