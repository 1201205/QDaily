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
class CuriosityProviderUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            backgroundColor = getResourceColor(R.color.white)
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                simpleDraweeView {
                    id = R.id.sdv_icon
                    roundAsCircle()
                }.lparams(width = dip(25), height = dip(25)) {
                    leftMargin = dip(18)
                }
                textView {
                    id = R.id.tv_name
                }.lparams(width = wrapContent, height = wrapContent) {
                    leftMargin = dip(10)
                }
                space {}.lparams(height = wrapContent, width = 0, weight = 1f)
                imageView {
                    id = R.id.iv_share
                    backgroundResource = R.drawable.icon_share
                }.lparams(width = wrapContent, height = wrapContent) {
                    rightMargin = dip(18)
                }
            }.lparams(width = matchParent, height = dip(50))
            relativeLayout {
                simpleDraweeView { id = R.id.sdv_img }.lparams(height = matchParent, width = matchParent)
                simpleDraweeView {
                    id = R.id.sdv_lab
                }.lparams(width = dip(30), height = dip(30)) {
                    alignParentBottom()
                    leftMargin = dip(18)
                    bottomMargin = dip(18)
                }
            }.lparams(width = matchParent, height = dip(180))
            textView {
                bold()
                textSize = 15f
                textColor = getResourceColor(R.color.text_color_normal)
                id = R.id.tv_title
            }.lparams(height = wrapContent, width = wrapContent) {
                rightMargin = dip(18)
                topMargin = dip(15)
                leftMargin = dip(18)
            }
            textView {
                textSize = 13f
                textColor = getResourceColor(R.color.text_small)
                id = R.id.tv_des
            }.lparams(height = wrapContent, width = wrapContent) {
                rightMargin = dip(18)
                topMargin = dip(5)
                leftMargin = dip(18)
                bottomMargin = dip(15)
            }
        }
        /**

        <TextView
        android:id="@+id/tv_des"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="@color/text_small"
        android:layout_marginRight="18dp"
        android:layout_marginTop="5dp"
        android:layout_marginLeft="18dp"
        android:layout_marginBottom="15dp"/>
        </LinearLayout>

         */
    }.view

    override fun unbind(t: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}