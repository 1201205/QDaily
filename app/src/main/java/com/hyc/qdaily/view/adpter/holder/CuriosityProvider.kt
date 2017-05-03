package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.anko.CuriosityProviderUI
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.loadUrl
import org.jetbrains.anko.find

/**
 * Created by ray on 17/3/12.
 */
class CuriosityProvider : ItemViewProvider<CuriosityProvider.CuriosityHolder, ViewData<Feed>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): CuriosityHolder {
        return CuriosityHolder(CuriosityProviderUI().bind(viewGroup!!.context))
    }

    override fun onBindViewHolder(holder: CuriosityHolder, data: ViewData<Feed>, position: Int, wrapper: ParamWrapper) {
        with(holder) {
            var feed = data.content
            loadUrl(sdvIcon, feed?.post?.column?.icon)
            loadUrl(sdvImg, feed?.image)
            loadUrl(sdvLab, feed?.post?.category?.image_lab)
            tvName.text = feed?.post?.column?.name
            tvDes.text = feed?.post?.description
            tvTitle.text = feed?.post?.title
        }
    }

    class CuriosityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sdvIcon: SimpleDraweeView = itemView.find(R.id.sdv_icon)
        var sdvImg: SimpleDraweeView = itemView.find(R.id.sdv_img)
        var tvName: TextView = itemView.find(R.id.tv_name)
        var sdvLab: SimpleDraweeView = itemView.find(R.id.sdv_lab)
        var tvDes: TextView = itemView.find(R.id.tv_des)
        var tvTitle: TextView = itemView.find(R.id.tv_title)
    }
}