package com.hyc.qdaily

import android.app.Application
import com.hyc.skin.core.SkinManager

/**
 * Created by hyc on 2017/3/3.
 */

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        SkinManager.getInstance().init(this)
    }

    companion object {
        lateinit var instance: MainApplication
            private set
    }
}
