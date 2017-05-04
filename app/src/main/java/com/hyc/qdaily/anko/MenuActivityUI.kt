package com.hyc.qdaily.anko

import android.content.Context
import android.view.Gravity
import android.view.View
import com.hyc.qdaily.R
import com.hyc.qdaily.util.getResourceColor
import org.jetbrains.anko.*

/**
 * Created by ray on 17/5/4.
 */
class MenuActivityUI : ViewBinder<Context> {
    override fun bind(c: Context): View = c.UI {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            id = R.id.ll_main
            backgroundColor = getResourceColor(R.color.main_bg)
            verticalLayout {
                id = R.id.ll_horizontal
                textView {
                    leftPadding = dip(15)
                    compoundDrawablePadding = dip(4)
                    gravity = Gravity.CENTER_VERTICAL
                    textSize = 16f
                    textColor = getResourceColor(R.color.text_color_normal)
                    backgroundResource = R.drawable.menu_search_bg
                    drawableLeft(R.drawable.icon_menu_search)
                    text = "搜索"
                }.lparams(width = matchParent, height = dip(40))
                linearLayout {
                    textView(R.style.menu_horizontal) {
                        text = "设置"
                        drawableTop(R.drawable.icon_menu_setting)
                    }
                    textView(R.style.menu_horizontal) {
                        text = "夜间"
                        drawableTop(R.drawable.icon_menu)
                    }
                    textView(R.style.menu_horizontal) {
                        text = "离线"
                        drawableTop(R.drawable.icon_menu_outline)
                    }
                    textView(R.style.menu_horizontal) {
                        text = "推荐"
                        drawableTop(R.drawable.icon_menu_share)
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(25)
                }
            }.lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(20)
                leftMargin = dip(20)
                rightMargin = dip(20)
            }
            frameLayout {
                verticalLayout {
                    id = R.id.ll_vertical
                    textView(R.style.menu_vertical) {
                        drawableLeft(R.drawable.icon_menu_about)
                        text = "关于我们"
                    }
                    textView(R.style.menu_vertical) {
                        drawableLeft(R.drawable.icon_menu_about)
                        drawableRight(R.drawable.icon_menu_second)
                        text = "新闻分类"
                        id = R.id.tv_classify
                    }
                    textView(R.style.menu_vertical) {
                        drawableLeft(R.drawable.icon_menu_about)
                        text = "关于我们"
                    }
                    textView(R.style.menu_vertical) {
                        drawableLeft(R.drawable.icon_menu_about)
                        text = "关于我们"
                    }
                    textView(R.style.menu_vertical) {
                        drawableLeft(R.drawable.icon_menu_about)
                        text = "关于我们"
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(25)
                    leftMargin = dip(20)
                    rightMargin = dip(20)
                    bottomMargin = dip(20)
                }
            }.lparams(width = matchParent, height = 0, weight = 1f)
        }
        /**


        <com.hyc.skin.widget.SkinTextView
        android:clickable="true"
        style="@style/menu_vertical"
        android:drawableLeft="@drawable/icon_menu_column"
        android:text="栏目中心"/>

        <com.hyc.skin.widget.SkinTextView
        style="@style/menu_vertical"
        android:drawableLeft="@drawable/icon_menu_notification"
        android:text="我的消息"/>

        <com.hyc.skin.widget.SkinTextView
        style="@style/menu_vertical"
        android:drawableLeft="@drawable/icon_menu_usercenter"
        android:text="个人中心"/>

        <com.hyc.skin.widget.SkinTextView
        style="@style/menu_vertical"
        android:drawableLeft="@drawable/icon_menu_feedback"
        android:text="意见反馈"/>

        <ImageButton
        android:id="@+id/ib_back"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:background="@drawable/menu_cancel_selector"/>
        </com.hyc.skin.widget.SkinLinearLayout>

        <com.hyc.skin.widget.SkinLinearLayout
        android:layout_marginBottom="20dp"
        android:layout_marginLeft="20dp"
        android:layout_marginRight="20dp"
        android:layout_marginTop="23dp"
        android:id="@+id/ll_classify"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:visibility="invisible">

        <android.support.v7.widget.RecyclerView
        android:id="@+id/rv_classify"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

        <ImageButton
        android:id="@+id/ib_classify"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:background="@drawable/menu_cancel_selector"/>
        </com.hyc.skin.widget.SkinLinearLayout>
        </FrameLayout>
        </com.hyc.skin.widget.SkinLinearLayout>

         */
    }.view

    override fun unbind(t: Context) {
    }

}