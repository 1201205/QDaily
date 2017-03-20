package com.hyc.qdaily.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by ray on 16/8/31.
 */
abstract class BaseFragment<T : BasePresenter<*>> : Fragment() {
    protected var mPresenter: T? = null
    protected var mUnbinder: Unbinder? = null
    protected abstract fun initPresenter()
    protected abstract fun getLayoutID(): Int
    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(getLayoutID(), container, false)
        mUnbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDetach() {
        super.onDetach()
        mUnbinder?.unbind()
    }
}
