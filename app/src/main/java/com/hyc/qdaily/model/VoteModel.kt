package com.hyc.qdaily.model

import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.home.Post
import com.hyc.qdaily.beans.paper.Option
import com.hyc.qdaily.beans.paper.PaperDetail
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.VoteContract

/**
 * Created by hyc on 2017/3/28.
 */
class VoteModel : VoteContract.Model {
    var data: ArrayList<ViewData<*>> = ArrayList()

    override fun revertToViewData(bean: BaseBean<PaperDetail>?):ArrayList<ViewData<*>> {
        var detail = bean?.response
        detail?.let {
            addHead(detail.post)
            addOption(detail.questions?.get(0)?.options)
            addSubmitButton()
        }
        return data
    }

    private fun addSubmitButton() {
        var viewData = ViewData<Void>()
        viewData.type = "submit"
        data.add(viewData)
    }

    private fun addOption(options: ArrayList<Option>?) {
        options?.forEach {
            var viewData = ViewData<Option>()
            viewData.content = it
            viewData.type = "vote_option"
            data.add(viewData)
        }
    }

    fun addHead(post: Post?) {
        var viewData = ViewData<Feed>()
        var feed = Feed()
        viewData.type = "lab"
        feed.post = post
        viewData.content = feed
        data.add(viewData)
    }
}