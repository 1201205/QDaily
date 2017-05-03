package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.anko.LabProviderUI
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.events.JumpPaperDetailEvent
import com.hyc.qdaily.util.loadUrl
import org.greenrobot.eventbus.EventBus


/**
 * Created by ray on 17/3/12.
 */
class LabHeaderProvider : ItemViewProvider<LabHeaderProvider.LabHolder, ViewData<Feed>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): LabHolder {
        var itemView = LabProviderUI().bind(viewGroup!!.context)
        val targetParams = itemView.layoutParams
        val StaggerLayoutParams: StaggeredGridLayoutManager.LayoutParams
        if (targetParams != null) {
            StaggerLayoutParams = StaggeredGridLayoutManager.LayoutParams(targetParams!!.width, targetParams!!.height)
        } else {
            StaggerLayoutParams = StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        StaggerLayoutParams.isFullSpan = true
        itemView.layoutParams = StaggerLayoutParams

        return LabHolder(itemView)
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
                EventBus.getDefault().post(JumpPaperDetailEvent(feed!!.post!!.id.toString(),feed.post!!.genre))
            }
        }
    }

    class LabHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.sdv_img)
        lateinit var sdvImg: SimpleDraweeView
        @BindView(R.id.sdv_icon)
        lateinit var sdvIcon: SimpleDraweeView
        @BindView(R.id.tv_des)
        lateinit var tvDes: TextView
        @BindView(R.id.tv_title)
        lateinit var tvTitle: TextView
        @BindView(R.id.ll_count)
        lateinit var llCount: LinearLayout
        @BindView(R.id.tv_count)
        lateinit var tvCount: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}