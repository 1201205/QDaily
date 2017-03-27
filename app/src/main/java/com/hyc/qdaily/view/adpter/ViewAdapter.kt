package com.hyc.qdaily.view.adpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.view.adpter.holder.ParamWrapper
import com.hyc.qdaily.view.adpter.holder.ViewModelPool

/**
 * Created by hyc on 2017/3/8.
 */
class ViewAdapter private constructor(context: Context, data: ArrayList<ViewData<*>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mData: ArrayList<ViewData<*>> = data
    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var paramWrapper: ParamWrapper = ParamWrapper()
    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder ?: let {
            return
        }
        ViewModelPool.instance.getProviderByType(mData[position].type)?.onBindViewHolder(holder, mData[position], position, paramWrapper)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ViewModelPool.instance.getProvider(viewType).onCreateViewHolder(mInflater, parent)
    }

    override fun getItemViewType(position: Int): Int {
        return ViewModelPool.instance.getType(mData[position].type)
    }

    fun getData(index: Int): ViewData<*> {
        return mData[index]
    }

    fun addData(data: ArrayList<ViewData<*>>) {
        mData.addAll(data)
    }

    class Builder {
        var adapter: ViewAdapter

        constructor(context: Context, data: ArrayList<ViewData<*>>) {
            adapter = ViewAdapter(context, data)
        }

        constructor(context: Context) {
            adapter = ViewAdapter(context, ArrayList())
        }

        fun build(): ViewAdapter {
            return adapter
        }
    }

}