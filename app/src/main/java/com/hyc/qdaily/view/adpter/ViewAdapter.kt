package com.hyc.qdaily.view.adpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hyc.qdaily.beans.ViewData

/**
 * Created by hyc on 2017/3/8.
 */
class ViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mDatas: ArrayList<ViewData>
    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}