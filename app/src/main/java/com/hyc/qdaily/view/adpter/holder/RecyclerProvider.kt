package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.LinearLayoutManager
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
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.loadUrl
import com.hyc.qdaily.view.adpter.ViewAdapter

/**
 * Created by ray on 17/3/12.
 */
class RecyclerProvider : ItemViewProvider<RecyclerProvider.RecyclerHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): RecyclerHolder {
        return RecyclerHolder(inflater.inflate(R.layout.item_recycler,viewGroup,false))
    }

    override fun onBindViewHolder(holder: RecyclerHolder, data: ViewData, position: Int, wrapper: ParamWrapper) {
        var columnContent=data.columnContent
        with(holder){
            var context=rvLanguage.context
            loadUrl(sdvIcon,columnContent?.icon)
            tvName.text=columnContent?.name
            rvLanguage.adapter=ViewAdapter.Builder(context,columnContent?.feeds!!).build()
            rvLanguage.layoutManager?:let {
                rvLanguage.layoutManager=LinearLayoutManager(context).also { it.orientation=LinearLayoutManager.HORIZONTAL }
            }
        }
    }

    class RecyclerHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        @BindView(R.id.rv_language)
        lateinit var rvLanguage:RecyclerView
        @BindView(R.id.sdv_icon)
        lateinit var sdvIcon:SimpleDraweeView
        @BindView(R.id.tv_name)
        lateinit var tvName:TextView
        init {
            ButterKnife.bind(this,itemView)
        }
    }
}