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
import com.hyc.qdaily.beans.other.LeftSideBar
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.events.JumpCategoryEvent
import com.hyc.qdaily.util.loadUrl
import org.greenrobot.eventbus.EventBus

/**
 * Created by hyc on 2017/3/27.
 */
class ClassifyProvider : ItemViewProvider<ClassifyProvider.ClassifyHolder, ViewData<LeftSideBar>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): ClassifyHolder {
        return ClassifyHolder(inflater.inflate(R.layout.item_classify, viewGroup, false))
    }

    override fun onBindViewHolder(holder: ClassifyHolder, data: ViewData<LeftSideBar>, position: Int, wrapper: ParamWrapper) {
        var leftBar = data.content
        with(holder) {
            leftBar?.let {
                tvName.text = leftBar.title
                loadUrl(sdvIcon, leftBar.white_icon)
                itemView.setOnClickListener {
                    EventBus.getDefault().post(JumpCategoryEvent(leftBar.id.toString(), leftBar.title!!))
                }
            }
        }

    }

    class ClassifyHolder(item: View) : RecyclerView.ViewHolder(item) {
        @BindView(R.id.sdv_icon)
        lateinit var sdvIcon: SimpleDraweeView
        @BindView(R.id.tv_name)
        lateinit var tvName: TextView

        init {
            ButterKnife.bind(this, item)
        }
    }
}