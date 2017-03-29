package com.hyc.qdaily.model

import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.paper.Option
import com.hyc.qdaily.beans.paper.PaperDetail
import com.hyc.qdaily.beans.paper.PaperOptions
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.SayContract

/**
 * Created by hyc on 2017/3/29.
 */
class SayModel : SayContract.Model {
    private var data: ArrayList<ViewData<*>> = ArrayList()

    override fun optionToViewData(bean: BaseBean<PaperOptions>,hasMore:Boolean): ArrayList<ViewData<*>> {
        var list = bean.response?.options
        list?.forEach {
            var viewData = ViewData<Option>()
            viewData.type = "say"
            viewData.content = it
            data.add(viewData)
        }
        if (!hasMore) {
            var viewData=ViewData<Void>()
            viewData.type="sayFooter"
            data.add(viewData)
        }
        return data
    }

    override fun revertToViewData(bean: BaseBean<PaperDetail>): ArrayList<ViewData<*>> {
        var viewData = ViewData<Feed>()
        var feed = Feed()
        viewData.type = "sayHead"
        feed.post = bean.response?.post
        viewData.content = feed
        data.add(viewData)
        return data
    }

}