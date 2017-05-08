package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.anko.SayProviderUI
import com.hyc.qdaily.beans.paper.Option
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.loadUrl
import com.hyc.skin.view.ExpandableTextView

/**
 * Created by hyc on 2017/3/29.
 */
class SayProvider : ItemViewProvider<SayProvider.SayHolder, ViewData<Option>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): SayHolder {
        return SayHolder(SayProviderUI().bind(viewGroup!!.context))
    }

    override fun onBindViewHolder(holder: SayHolder, data: ViewData<Option>, position: Int, wrapper: ParamWrapper) {
        var option = data.content
        with(holder) {
            loadUrl(sdvHead, option?.author?.avatar)
            tvContent.text = option?.content
            tvCount.text = option?.praise_count.toString()+" 赞"
            tvName.text = option?.author?.name
            var context=itemView.context
            var name="say_bg"+position%7
            var color=context.resources.getIdentifier(name,"color",context.packageName)
            Log.e("hyc-name",name+"----"+color)
            if (option?.collapsed!!) {
                tvContent.collapse()
            } else {
                tvContent.expand()
            }
            tvContent.setOnClickListener {
                option?.collapsed=(!option?.collapsed!!)
                if (tvContent.isExpanded) {
                    tvContent.collapse()
                } else {
                    tvContent.expand()
                }
            }
            llContent.setBackgroundColor(context.resources.getColor(color))
//            ivExpand.setOnClickListener { option?.collapsed=(!option?.collapsed!!) }
        }
    }

    class SayHolder(item: View) : RecyclerView.ViewHolder(item) {
        @BindView(R.id.sdv_head)
        lateinit var sdvHead: SimpleDraweeView
        @BindView(R.id.etv_content)
        lateinit var tvContent: ExpandableTextView
        @BindView(R.id.tv_name)
        lateinit var tvName: TextView
        @BindView(R.id.tv_praise_count)
        lateinit var tvCount: TextView
        @BindView(R.id.rl_content)
        lateinit var llContent:RelativeLayout

        init {
            ButterKnife.bind(this, item)
        }
    }
}