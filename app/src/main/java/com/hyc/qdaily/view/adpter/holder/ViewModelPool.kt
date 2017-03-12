package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlin.properties.Delegates

/**
 * Created by hyc on 2017/3/8.
 */
class ViewModelPool private constructor() {
    private var mTypeMap: HashMap<String, ItemViewProvider<RecyclerView.ViewHolder>> = HashMap()
    private var mProviders: ArrayList<ItemViewProvider<RecyclerView.ViewHolder>> = ArrayList()

    companion object {
        var instance: ViewModelPool=ViewModelPool()
    }

    private fun putInPool(type: String, provider: ItemViewProvider<RecyclerView.ViewHolder>) {
        mTypeMap.put(type, provider)
        mProviders.add(provider)
    }

    fun getType(type: String): Int = mProviders.indexOf(mTypeMap[type])
    fun getProviderByType(type: String): ItemViewProvider<RecyclerView.ViewHolder>? = mTypeMap[type]
    fun getProvider(index: Int): ItemViewProvider<RecyclerView.ViewHolder> {
        Log.e("hyc-we",this.toString()+"Provider---"+mProviders.size+"++++++"+index)
        return mProviders[index]
    }

    fun init(){
        putInPool("banner",BannerProvider() as ItemViewProvider<RecyclerView.ViewHolder>)
        putInPool("feed",FeedProvider() as ItemViewProvider<RecyclerView.ViewHolder>)
        putInPool("curiosity",CuriosityProvider() as ItemViewProvider<RecyclerView.ViewHolder>)
        putInPool("vertical",VerticalProvider() as ItemViewProvider<RecyclerView.ViewHolder>)
        putInPool("recycler",RecyclerProvider() as ItemViewProvider<RecyclerView.ViewHolder>)
        putInPool("language",LanguageProvider() as ItemViewProvider<RecyclerView.ViewHolder>)

        Log.e("hyc-we",this.toString()+"init---"+mProviders.size)
    }

}