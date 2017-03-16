package com.hyc.qdaily.presenter

import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.contract.LabContract
import com.hyc.qdaily.model.LabModel
import com.hyc.qdaily.net.RequestClient

/**
 * Created by hyc on 17/3/15.
 */
class LabPresenter(mView: LabContract.View) : LabContract.Presenter, BasePresenter<LabContract.View>(mView) {
    var model: LabModel = LabModel()
    override fun getLab() {
        RequestClient.api.getLabByIndex("0").compose(SchedulerTransformer.create()).map { result -> model.revertToViewData(result) }.subscribe({ result -> mView.showRecommendData(result) }, { onError(it) })
    }

    override fun getMoreLab(index: String) {

    }
}