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
class CuriosityProvider : ItemViewProvider<CuriosityProvider.CuriosityHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): CuriosityHolder {
        return CuriosityHolder(inflater.inflate(R.layout.item_curiosity,viewGroup,false))
    }

    override fun onBindViewHolder(holder: CuriosityHolder, data: ViewData, position: Int, wrapper: ParamWrapper) {
        with(holder){
            loadUrl(sdvIcon,data.feed?.post?.column?.icon)
            loadUrl(sdvImg,data.feed?.image)
            loadUrl(sdvLab,data.feed?.post?.category?.image_lab)
            tvName.text=data.feed?.post?.column?.name
            tvDes.text=data.feed?.post?.description
            tvTitle.text=data.feed?.post?.title
        }
    }

    class CuriosityHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.sdv_icon)
        lateinit var sdvIcon: SimpleDraweeView
        @BindView(R.id.sdv_img)
        lateinit var sdvImg: SimpleDraweeView
        @BindView(R.id.tv_name)
        lateinit var tvName:TextView
        @BindView(R.id.sdv_lab)
        lateinit var sdvLab:SimpleDraweeView
        @BindView(R.id.tv_des)
        lateinit var tvDes: TextView
        @BindView(R.id.tv_title)
        lateinit var tvTitle: TextView
        init {
            ButterKnife.bind(this,itemView)
        }
    }
}