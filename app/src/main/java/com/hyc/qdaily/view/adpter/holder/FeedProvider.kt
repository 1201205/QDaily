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
import com.hyc.qdaily.util.getTime
import com.hyc.qdaily.util.loadUrl

/**
 * Created by ray on 17/3/9.
 */



class FeedProvider : ItemViewProvider<FeedProvider.FeedViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): FeedViewHolder {
            return FeedViewHolder(inflater.inflate(R.layout.item_feed,viewGroup,false))
    }

    override fun onBindViewHolder(holder: FeedViewHolder, data: ViewData, position: Int, wrapper: ParamWrapper) {
        with(holder){
            var feed=data.feed
            feed?.let{
                loadUrl(sdvImg,feed.image)
                tvCatTitle.text=feed.post?.category?.title
                tvCommentCount.text=(feed.post?.comment_count).toString()
                tvDescription.text=feed.post?.title
                tvPraiseCount.text=feed.post?.praise_count.toString()
                tvTime.text= getTime(feed.post?.publish_time!!)
            }
        }
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        @BindView(R.id.tv_cat_title)
        lateinit var tvCatTitle:TextView
        @BindView(R.id.tv_description)
        lateinit var tvDescription:TextView
        @BindView(R.id.sdv_img)
        lateinit var sdvImg:SimpleDraweeView
        @BindView(R.id.tv_comment_count)
        lateinit var tvCommentCount:TextView
        @BindView(R.id.tv_praise_count)
        lateinit var tvPraiseCount:TextView
        @BindView(R.id.tv_time)
        lateinit var tvTime:TextView
        init {
            ButterKnife.bind(this,itemView)
        }

    }
}