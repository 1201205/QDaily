package com.hyc.qdaily.model

import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.home.InsertContent
import com.hyc.qdaily.beans.home.Paper
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.LabContract

/**
 * Created by ray on 17/3/16.
 */
class LabModel:LabContract.Model<Paper>{
    private var datas: ArrayList<ViewData<*>> = ArrayList()
    override fun revertToViewData(bean: BaseBean<Paper>): ArrayList<ViewData<*>> {
        bean.response?.feeds?.let{
            if(it.size>0){
                it.forEach {
                    var data=ViewData<Feed>()
                    data.content=it
                    data.type="lab"
                    datas.add(data)
                }
            }
        }
        bean.response?.paper_topics?.let {
            if (it.size>0) {
                it.forEach {
                    var inserts=ArrayList<ViewData<InsertContent>>()
                    it.insert_content?.forEach {
                        var data=ViewData<InsertContent>()
                        data.type="topic"
                        data.content=it
                        inserts.add(data)
                    }
                    var data=ViewData<ArrayList<ViewData<InsertContent>>>()
                    data.content=inserts
                    data.type="topics"
                    datas.add(data)
                }
            }
        }
        return datas
    }

    override fun addToViewData(bean: BaseBean<Paper>) {
    }

    override fun getViewDatas(): ArrayList<ViewData<*>> {
        return datas
    }

}