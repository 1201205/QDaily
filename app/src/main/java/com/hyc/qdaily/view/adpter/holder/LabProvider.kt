package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.anko.LabProviderUI
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.events.JumpPaperDetailEvent
import com.hyc.qdaily.util.loadUrl
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.find

/**
 * Created by ray on 17/3/12.
 */
class LabProvider : ItemViewProvider<LabProvider.LabHolder, ViewData<Feed>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): LabHolder {
        return LabHolder(LabProviderUI().bind(viewGroup!!.context))
    }

    override fun onBindViewHolder(holder: LabHolder, data: ViewData<Feed>, position: Int, wrapper: ParamWrapper) {
        var feed = data.content
        with(holder) {
            loadUrl(sdvImg, feed?.post?.image)
            loadUrl(sdvIcon, feed?.post?.category?.image_lab)
            tvCount.text = feed?.post?.record_count.toString()
            tvDes.text = feed?.post?.description
            tvTitle.text = feed?.post?.title
            itemView.setOnClickListener {
                EventBus.getDefault().post(JumpPaperDetailEvent(feed!!.post!!.id.toString(), feed.post!!.genre))
            }
        }
    }

    class LabHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sdvImg: SimpleDraweeView = itemView.find(R.id.sdv_img)
        var sdvIcon: SimpleDraweeView = itemView.find(R.id.sdv_icon)
        var tvDes: TextView = itemView.find(R.id.tv_des)
        var tvTitle: TextView = itemView.find(R.id.tv_title)
        var llCount: LinearLayout = itemView.find(R.id.ll_count)
        var tvCount: TextView = itemView.find(R.id.tv_count)
    }
}