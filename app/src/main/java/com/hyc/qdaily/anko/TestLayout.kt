package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.hyc.qdaily.R
import org.jetbrains.anko.*
import com.hyc.qdaily.util.getResourceColor


/**
 * Created by hyc on 2017/4/25.
 */
class TestLayout : ViewBinder<Context> {
    override fun bind(t: Context): View = t.UI {
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
//                        textSize = sp(16).toFloat()
                        id = R.id.tv_title
                        textColor= getResourceColor(R.color.white)
//                        setTextColor(R.color.white)
                    }.lparams(width = wrapContent, height = wrapContent)

                    textView {
                        maxLines = 2
//                        textSize = sp(12).toFloat()
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