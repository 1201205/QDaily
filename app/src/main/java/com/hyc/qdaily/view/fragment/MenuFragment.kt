package com.hyc.qdaily.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.hyc.qdaily.R

/**
 * Created by hyc on 2017/3/22.
 */
class MenuFragment : Fragment() {
    @BindView(R.id.ll_horizontal)
    lateinit var llHorizontal: LinearLayout
    @BindView(R.id.ll_vertical)
    lateinit var llVertical: LinearLayout

    var mUnbinder: Unbinder? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater!!.inflate(R.layout.fragment_menu, container, false)
        mUnbinder = ButterKnife.bind(this, v)
        return v
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    override fun onDetach() {
        mUnbinder?.unbind()
        super.onDetach()
    }
}