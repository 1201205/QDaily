package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyc.qdaily.anko.SayFooterProviderUI
import com.hyc.qdaily.beans.view.ViewData

/**
 * Created by hyc on 2017/3/29.
 */
class SayFooterProvider : ItemViewProvider<SayFooterProvider.SayFooterHolder, ViewData<Void>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): SayFooterHolder {
        var itemView = SayFooterProviderUI().bind(viewGroup!!.context)
        val targetParams = itemView.layoutParams
        val StaggerLayoutParams: StaggeredGridLayoutManager.LayoutParams
        if (targetParams != null) {
            StaggerLayoutParams = StaggeredGridLayoutManager.LayoutParams(targetParams!!.width, targetParams!!.height)
        } else {
            StaggerLayoutParams = StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        StaggerLayoutParams.isFullSpan = true
        itemView.layoutParams = StaggerLayoutParams

        return SayFooterProvider.SayFooterHolder(itemView)
    }

    override fun onBindViewHolder(holder: SayFooterHolder, data: ViewData<Void>, position: Int, wrapper: ParamWrapper) {
    }

    class SayFooterHolder(item: View) : RecyclerView.ViewHolder(item)
}