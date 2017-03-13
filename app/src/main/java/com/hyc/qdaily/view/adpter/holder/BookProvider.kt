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
class BookProvider : ItemViewProvider<BookProvider.BookHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): BookHolder {
        return BookHolder(inflater.inflate(R.layout.item_book, viewGroup, false))
    }

    override fun onBindViewHolder(holder: BookHolder, data: ViewData, position: Int, wrapper: ParamWrapper) {
        var feed = data.feed
        with(holder) {
            loadUrl(sdvImg, feed?.image)
            tvCommentCount.text = feed?.post?.comment_count.toString()
            tvPraiseCount.text = feed?.post?.praise_count.toString()
            tvTitle.text = data.feed?.post?.title
        }
    }

    class BookHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.sdv_img)
        lateinit var sdvImg: SimpleDraweeView
        @BindView(R.id.tv_title)
        lateinit var tvTitle: TextView
        @BindView(R.id.tv_praise_count)
        lateinit var tvPraiseCount: TextView
        @BindView(R.id.tv_comment_count)
        lateinit var tvCommentCount: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}