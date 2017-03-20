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
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.loadUrl

/**
 * Created by ray on 17/3/12.
 */
class TopicProvider : ItemViewProvider<TopicProvider.TopicHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): TopicHolder {
        return TopicHolder(inflater.inflate(R.layout.item_topic, viewGroup, false))
    }

    override fun onBindViewHolder(holder: TopicHolder, data: ViewData, position: Int, wrapper: ParamWrapper) {
        var insert = data.insertContent
        with(holder) {
            loadUrl(sdvImg, insert?.image)
            tvTitle.text = insert?.title
            tvDes.text = insert?.description
        }
    }

    class TopicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.sdv_img)
        lateinit var sdvImg: SimpleDraweeView
        @BindView(R.id.tv_title)
        lateinit var tvTitle: TextView
        @BindView(R.id.tv_des)
        lateinit var tvDes: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}