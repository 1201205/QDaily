package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.beans.paper.Option
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.loadUrl

/**
 * Created by hyc on 2017/3/28.
 */
class VoteProvider : ItemViewProvider<VoteProvider.VoteHolder, ViewData<Option>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): VoteHolder {
        return VoteHolder(inflater.inflate(R.layout.item_vote, viewGroup, false))
    }

    override fun onBindViewHolder(holder: VoteHolder, data: ViewData<Option>, position: Int, wrapper: ParamWrapper) {
        data.content?.let {
            loadUrl(holder.sdvIcon, it!!.image)
            holder.tvDes.text = it.content
            if (it.selected) {
                holder.ivVote.setBackgroundResource(R.drawable.icon_checked_multiple_choice)
            } else {
                holder.ivVote.setBackgroundResource(R.drawable.icon_check_multiple_choice)
            }
            holder.itemView.setOnClickListener {
                data.content!!.selected = !data.content!!.selected
                if (data.content!!.selected) {
                    holder.ivVote.setBackgroundResource(R.drawable.icon_checked_multiple_choice)
                } else {
                    holder.ivVote.setBackgroundResource(R.drawable.icon_check_multiple_choice)
                }
            }
        }
    }

    class VoteHolder(item: View) : RecyclerView.ViewHolder(item) {
        @BindView(R.id.sdv_icon)
        lateinit var sdvIcon: SimpleDraweeView
        @BindView(R.id.tv_des)
        lateinit var tvDes: TextView
        @BindView(R.id.iv_vote)
        lateinit var ivVote: ImageView

        init {
            ButterKnife.bind(this, item)
        }
    }
}