package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView

/**
 * Created by hyc on 2017/3/8.
 */
class ViewModelPool private constructor() {
    private var mTypeMap: HashMap<String, ItemViewProvider<RecyclerView.ViewHolder>> = HashMap()
    private var mProviders: ArrayList<ItemViewProvider<RecyclerView.ViewHolder>> = ArrayList()

    companion object {
        fun getInstance(): ViewModelPool = ViewModelPool()
    }

    private fun putInPool(type: String, provider: ItemViewProvider<RecyclerView.ViewHolder>) {
        mTypeMap.put(type, provider)
        mProviders.add(provider)
    }

    fun getType(type: String): Int = mProviders.indexOf(mTypeMap[type])
    fun getProviderByType(type: String): ItemViewProvider<RecyclerView.ViewHolder>? = mTypeMap[type]
    fun getProvider(index: Int): ItemViewProvider<RecyclerView.ViewHolder> = mProviders[index]

    fun init(){
        putInPool("banner",BannerProvider() as ItemViewProvider<RecyclerView.ViewHolder>)
    }

}