package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.beans.home.Feed

/**
 * Created by hyc on 2017/3/8.
 */
abstract class ItemViewProvider<V : RecyclerView.ViewHolder>{
    abstract fun onCreateViewHolder(inflater: LayoutInflater,viewGroup: ViewGroup?):V
    abstract fun onBindViewHolder(holder:V, data: ViewData, position:Int, wrapper: ParamWrapper):Unit
}