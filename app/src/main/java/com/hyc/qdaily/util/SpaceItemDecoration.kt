package com.hyc.qdaily.util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by hyc on 2017/3/20.
 */
class SpaceItemDecoration(border: Int, center: Int) : RecyclerView.ItemDecoration() {
    var border: Int = border
    var center: Int = center

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        when (parent?.getChildAdapterPosition(view)) {
            0 -> outRect?.set(border, 0, center, 0)
            (parent?.adapter?.itemCount!! - 1) -> outRect?.set(center, 0, border, 0)
            else -> outRect?.set(center, 0, center, 0)
        }
    }
}