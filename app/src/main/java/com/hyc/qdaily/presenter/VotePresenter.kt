package com.hyc.qdaily.presenter

import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.contract.VoteContract
import com.hyc.qdaily.model.VoteModel
import com.hyc.qdaily.net.RequestClient

/**
 * Created by hyc on 2017/3/28.
 */
class VotePresenter(view: VoteContract.View) : BasePresenter<VoteContract.View>(view), VoteContract.Presenter {
    private var mModel = VoteModel()
    override fun getVoteByID(id: String) {
        RequestClient.api.getPaperDetail(id).compose(SchedulerTransformer.create()).map { mModel.revertToViewData(it) }.subscribe({
            mView.showVote(it)
        }, { onError(it) })
    }

}