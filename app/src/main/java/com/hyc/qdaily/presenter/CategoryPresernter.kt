package com.hyc.qdaily.presenter

import android.text.TextUtils
import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.other.Category
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.contract.CategoryContract
import com.hyc.qdaily.model.CategoryModel
import com.hyc.qdaily.net.RequestClient
import io.reactivex.functions.Function

/**
 * Created by ray on 17/3/27.
 */
class CategoryPresernter(view: CategoryContract.View) : BasePresenter<CategoryContract.View>(view), CategoryContract.Presenter {
    private var mModel: CategoryModel = CategoryModel()
    private lateinit var mID: String
    private var mLastKey: String? = null
    private var converter = Function<BaseBean<Category>, ArrayList<ViewData<*>>> {
        mLastKey = it.response?.last_key
        if (!(it.response?.has_more!!)||TextUtils.isEmpty(mLastKey)) {
            mView.noMore()
        }
        mModel.convertToViewData(it)
    }

    override fun getCategory(id: String) {
        mID = id
        RequestClient.api.getCategory(id, "0").compose(SchedulerTransformer.create()).map(converter).subscribe({
            mView.showCategory(it)
        }, { onError(it) })
    }

    override fun getMoreData() {
        if (TextUtils.isEmpty(mLastKey)) {
            return
        }
        RequestClient.api.getCategory(mID, mLastKey!!).compose(SchedulerTransformer.create()).map(converter).subscribe({
            mView.showMore(it)
        }, {
            onError(it)
        })
    }

}