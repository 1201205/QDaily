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
class LanguageProvider : ItemViewProvider<LanguageProvider.LanguageHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): LanguageHolder {
        return LanguageHolder(inflater.inflate(R.layout.item_language, viewGroup, false))
    }

    override fun onBindViewHolder(holder: LanguageHolder, data: ViewData, position: Int, wrapper: ParamWrapper) {
        var feed = data.feed
        with(holder) {
            loadUrl(sdvImg, feed?.image)
            tvCommentCount.text = feed?.post?.comment_count.toString()
            tvPraiseCount.text = feed?.post?.praise_count.toString()
            tvTime.text = feed?.post?.publish_time.toString()
            tvDes.text = data.feed?.post?.description
            tvTitle.text = data.feed?.post?.title
        }
    }

    class LanguageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.sdv_img)
        lateinit var sdvImg: SimpleDraweeView
        @BindView(R.id.tv_des)
        lateinit var tvDes: TextView
        @BindView(R.id.tv_title)
        lateinit var tvTitle: TextView
        @BindView(R.id.tv_praise_count)
        lateinit var tvPraiseCount: TextView
        @BindView(R.id.tv_time)
        lateinit var tvTime: TextView
        @BindView(R.id.tv_comment_count)
        lateinit var tvCommentCount: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}