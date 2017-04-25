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
import com.hyc.qdaily.anko.TestLayout
import com.hyc.qdaily.beans.home.InsertContent
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.loadUrl
import org.jetbrains.anko.find

/**
 * Created by ray on 17/3/12.
 */
class TopicProvider : ItemViewProvider<TopicProvider.TopicHolder, ViewData<InsertContent>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): TopicHolder {
        return TopicHolder(TestLayout().bind(viewGroup!!.context))
    }

    override fun onBindViewHolder(holder: TopicHolder, data: ViewData<InsertContent>, position: Int, wrapper: ParamWrapper) {
        var insert = data.content
        with(holder) {
            loadUrl(sdvImg, insert?.image)
            tvTitle.text = insert?.title
            tvDes.text = insert?.description
        }
    }

    class TopicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sdvImg: SimpleDraweeView = itemView.find(R.id.sdv_img)
        var tvTitle: TextView=itemView.find(R.id.tv_title)
        var tvDes: TextView=itemView.find(R.id.tv_des)

    }
}