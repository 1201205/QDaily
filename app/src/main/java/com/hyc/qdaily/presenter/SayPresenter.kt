package com.hyc.qdaily.presenter

import android.text.TextUtils
import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.contract.SayContract
import com.hyc.qdaily.model.SayModel
import com.hyc.qdaily.net.RequestClient

/**
 * Created by hyc on 2017/3/29.
 */
class SayPresenter(view: SayContract.View) : BasePresenter<SayContract.View>(view), SayContract.Presenter {
    private var mModel = SayModel()
    private var mID = ""
    private var mLastKey: String? = "0"

    override fun getContent(id: String) {
        mID = id
        RequestClient.api.getPaperDetail(id).compose(SchedulerTransformer.create()).map {
            mModel.revertToViewData(it)
            getOption()
        }.subscribe({}, { onError(it) })
    }

    override fun getOption() {
        if (TextUtils.isEmpty(mLastKey)) {
            return
        }
        RequestClient.api.getOptions(mID, mLastKey!!).compose(SchedulerTransformer.create()).subscribe({
            if (it.response == null) {
                return@subscribe
            }
            if (!it.response!!.has_more) {
                mView.noMore()
            }
            var data = mModel.optionToViewData(it)
            if ("0".equals(mLastKey)) {
                mView.showContent(data)
            } else {
                mView.showMore(data)
            }
            mLastKey = it.response!!.last_key
        }, { onError(it) })
    }
}