package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.viewPager

/**
 * Created by ray on 17/4/25.
 */
class BannerLayout:ViewBinder<Context>{
    override fun bind(t: Context): View =t.UI {
        relativeLayout{
            lparams(width= matchParent,height = wrapContent)
            backgroundColor= getResourceColor(R.color.content_bg)

            viewPager {
                lparams(width= matchParent,height = dip(250))
                id=R.id.vp_banner
            }
            linearLayout {
                id=R.id.ll_indicator
                orientation=LinearLayout.HORIZONTAL
            }.lparams(width= wrapContent,height = wrapContent){
                alignParentBottom()
                centerHorizontally()
                gravity=Gravity.CENTER_HORIZONTAL
                bottomMargin=dip(30)
            }

        }
    }.view

    override fun unbind(t: Context) {
    }

}