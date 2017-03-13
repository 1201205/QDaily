package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.hyc.qdaily.R
import com.hyc.qdaily.beans.view.ViewData

/**
 * Created by ray on 17/3/13.
 */
class HeadLineProvider: ItemViewProvider<HeadLineProvider.HeadLineHolder>(){
    override fun onBindViewHolder(holder: HeadLineHolder, data: ViewData, position: Int, wrapper: ParamWrapper) {

        data.headLine?.list?.let {
            with(holder){
                if (data.headLine!!.list?.size==0) {
                    return
                }
                tvLine1.text= data.headLine!!.list!![0].description
                tvLine2.text= data.headLine!!.list!![1].description
                tvLine3.text= data.headLine!!.list!![2].description
            }
        }


    }

    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): HeadLineHolder {
        return HeadLineHolder(inflater.inflate(R.layout.item_head_line,viewGroup,false))
    }

    class HeadLineHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        @BindView(R.id.tv_line1)
        lateinit var tvLine1:TextView
        @BindView(R.id.tv_line2)
        lateinit var tvLine2:TextView
        @BindView(R.id.tv_line3)
        lateinit var tvLine3:TextView
        @BindView(R.id.tv_detail)
        lateinit var tvDetail:TextView
        init {
            ButterKnife.bind(this,itemView)
        }
    }
}