package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.loadUrl

/**
 * Created by ray on 17/3/12.
 */
class CuriosityProvider : ItemViewProvider<CuriosityProvider.CuriosityHolder, ViewData<Feed>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): CuriosityHolder {
        return CuriosityHolder(inflater.inflate(R.layout.item_curiosity, viewGroup, false))
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
        @BindView(R.id.sdv_icon)
        lateinit var sdvIcon: SimpleDraweeView
        @BindView(R.id.sdv_img)
        lateinit var sdvImg: SimpleDraweeView
        @BindView(R.id.tv_name)
        lateinit var tvName: TextView
        @BindView(R.id.sdv_lab)
        lateinit var sdvLab: SimpleDraweeView
        @BindView(R.id.tv_des)
        lateinit var tvDes: TextView
        @BindView(R.id.tv_title)
        lateinit var tvTitle: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}