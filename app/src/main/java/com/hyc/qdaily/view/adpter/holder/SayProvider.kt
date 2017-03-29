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
import com.hyc.qdaily.beans.paper.Option
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.loadUrl

/**
 * Created by hyc on 2017/3/29.
 */
class SayProvider : ItemViewProvider<SayProvider.SayHolder, ViewData<Option>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): SayHolder {
        return SayHolder(inflater.inflate(R.layout.item_say, viewGroup, false))
    }

    override fun onBindViewHolder(holder: SayHolder, data: ViewData<Option>, position: Int, wrapper: ParamWrapper) {
        var option = data.content
        with(holder) {
            loadUrl(sdvHead, option?.author?.avatar)
            tvContent.text = option?.content
            tvCount.text = option?.praise_count.toString()
            tvName.text = option?.author?.name
        }
    }

    class SayHolder(item: View) : RecyclerView.ViewHolder(item) {
        @BindView(R.id.sdv_head)
        lateinit var sdvHead: SimpleDraweeView
        @BindView(R.id.tv_content)
        lateinit var tvContent: TextView
        @BindView(R.id.tv_name)
        lateinit var tvName: TextView
        @BindView(R.id.tv_praise_count)
        lateinit var tvCount: TextView

        init {
            ButterKnife.bind(this, item)
        }
    }
}