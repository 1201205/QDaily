package com.hyc.qdaily.contract

import com.hyc.qdaily.base.BaseView
import com.hyc.qdaily.beans.paper.Tot

/**
 * Created by ray on 17/4/18.
 */
interface TotContract {
    interface View : BaseView {
        fun showContent(tot: Tot)
    }

    interface Presenter {
        fun getTot(id: String)
    }

    interface Model {

    }
}