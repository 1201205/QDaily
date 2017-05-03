package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.anko.BookProviderUI
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.loadUrl
import org.jetbrains.anko.find

/**
 * Created by ray on 17/3/12.
 */
class BookProvider : ItemViewProvider<BookProvider.BookHolder, ViewData<Feed>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): BookHolder {
        return BookHolder(BookProviderUI().bind(viewGroup!!.context))
    }

    override fun onBindViewHolder(holder: BookHolder, data: ViewData<Feed>, position: Int, wrapper: ParamWrapper) {
        var feed = data.content
        with(holder) {
            loadUrl(sdvImg, feed?.image)
            tvCommentCount.text = feed?.post?.comment_count.toString()
            tvPraiseCount.text = feed?.post?.praise_count.toString()
            tvTitle.text = feed?.post?.title
        }
    }

    class BookHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sdvImg: SimpleDraweeView = itemView.find(R.id.sdv_img)
        var tvTitle: TextView = itemView.find(R.id.tv_title)
        var tvPraiseCount: TextView = itemView.find(R.id.tv_praise_count)
        var tvCommentCount: TextView = itemView.find(R.id.tv_comment_count)

    }
}