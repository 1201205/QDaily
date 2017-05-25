package com.hyc.qdaily.file

import android.text.TextUtils
import com.hyc.qdaily.util.AppUtil
import com.hyc.qdaily.util.getFileName
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

/**
 * Created by hyc on 2017/3/24.
 */
class FileCacheLoader private constructor() {
    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { FileCacheLoader() }
    }

    fun loadFormCache(url: String?): InputStream? {
        if (TextUtils.isEmpty(url)) {
            return null
        }
        var targetFile = File(AppUtil.CACHE_PATH + File.separator + getFileName(url!!))
        if (targetFile.exists() && targetFile.length() > 0) {
            return FileInputStream(targetFile)
        }
        return null
    }
    fun getFile():File{
        var f=File(AppUtil.CACHE_PATH)
       return f.listFiles().first()
    }
}