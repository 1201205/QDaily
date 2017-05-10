package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyc.qdaily.R
import com.hyc.qdaily.beans.view.ViewData

/**
 * Created by hyc on 2017/3/28.
 */
class SubmitProvider : ItemViewProvider<SubmitProvider.SubmitHolder, ViewData<Void>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): SubmitHolder {
        return SubmitHolder(inflater.inflate(R.layout.item_submit, viewGroup, false))
    }

    override fun onBindViewHolder(holder: SubmitHolder, data: ViewData<Void>, position: Int, wrapper: ParamWrapper) {
        holder.itemView.setOnClickListener {
            //jump
        }
    }

    class SubmitHolder(item: View) : RecyclerView.ViewHolder(item) {
    }
}