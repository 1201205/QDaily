package com.hyc.qdaily.view.adpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyc.qdaily.beans.ViewData
import com.hyc.qdaily.view.adpter.holder.ParamWrapper
import com.hyc.qdaily.view.adpter.holder.ViewModelPool

/**
 * Created by hyc on 2017/3/8.
 */
class ViewAdapter private constructor(context: Context,datas: ArrayList<ViewData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private  var mDatas: ArrayList<ViewData> = datas
    private var mInflater:LayoutInflater = LayoutInflater.from(context)
    private var paramWrapper: ParamWrapper=ParamWrapper()
    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder?:let{
            return
        }
        Log.e("hyc-gg",mDatas[position].type);
        ViewModelPool.instance.getProviderByType(mDatas[position].type)?.onBindViewHolder(holder,mDatas[position],position,paramWrapper)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ViewModelPool.instance.getProvider(viewType).onCreateViewHolder(mInflater,parent)
    }

    override fun getItemViewType(position: Int): Int {
        return ViewModelPool.instance.getType(mDatas[position].type)
    }
    class Builder{
        var adapter: ViewAdapter

        constructor(context: Context,datas: ArrayList<ViewData>){
            adapter= ViewAdapter(context,datas)
        }
        constructor(context: Context){
            adapter= ViewAdapter(context, ArrayList())
        }
        fun build():ViewAdapter{
            return adapter
        }
    }

}