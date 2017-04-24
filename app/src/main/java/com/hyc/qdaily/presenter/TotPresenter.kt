package com.hyc.qdaily.presenter

import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.contract.TotContract
import com.hyc.qdaily.net.RequestClient

/**
 * Created by ray on 17/4/18.
 */
class TotPresenter(view:TotContract.View):BasePresenter<TotContract.View>(view),TotContract.Presenter{
    override fun getTot(id: String) {
        RequestClient.api.getTot(id).compose(SchedulerTransformer.create()).subscribe({
            if (it != null) {
                mView.showContent(it.response!!)
            } else {
                mView.onNetError()
            }
        },{onError(it)})
    }

}