package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*


/**
 * Created by hyc on 2017/4/25.
 */
class TopicProviderUI : ViewBinder<Context> {
    override fun bind(mainActivity: Context): View = mainActivity.UI {
            relativeLayout {
                lparams(width = dip(240), height = dip(140)) {
                    setMargins(0, dip(5), 0, dip(5))
                }
                simpleDraweeView {
                    lparams(width = dip(240), height = dip(140))
                    id = R.id.sdv_img
                }
                linearLayout {
                    gravity= Gravity.CENTER
                    orientation=LinearLayout.VERTICAL
                    lparams{
                        width = matchParent
                        height = wrapContent
                    }
                    textView {
                        textSize = 16f
                        id = R.id.tv_title
                        textColor= getResourceColor(R.color.white)
//                        setTextColor(R.color.white)
                    }.lparams(width = wrapContent, height = wrapContent)

                    textView {
                        maxLines = 2
                        textSize = 12f
                        id = R.id.tv_des
                        textColor= getResourceColor(R.color.white)
//                        setTextColor(R.color.alpha_white)
                    }.lparams(width = wrapContent, height = wrapContent) {
                        topMargin = dip(4)
                    }
                }.lparams{
                    addRule(RelativeLayout.CENTER_IN_PARENT)
                }

        }

    }.view

    override fun unbind(t: Context) {
    }
}