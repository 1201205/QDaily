package com.hyc.qdaily.file

import android.net.Uri
import android.util.Log
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.net.RequestClient
import com.hyc.qdaily.util.AppUtil
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.io.*

/**
 * Created by hyc on 2017/3/24.
 */
class FileDownLoader private constructor() {
    val TAG = "FileDownLoader"

    companion object {
        var instance = FileDownLoader()
    }

    fun downLoad(url: String, listener: Listener) {
        var targetFile = File(AppUtil.CACHE_PATH + File.separator + getFileName(url))
        if (targetFile.exists()) {
            if (targetFile.length() > 0) {
                listener.onSuccess(url)
            } else {
                targetFile.delete()
            }
        }
        targetFile.createNewFile()
        RequestClient.api.downLoadFile(url).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).map { result ->
            var inputStream: InputStream?=null
            var outputStream: OutputStream?=null
            var byteArray=result.source().readByteArray();
            var size=byteArray.size
            if (size==0) {
                return@map true
            }
            try {
                var readBuffer = ByteArray(4096)
                var read: Int
                inputStream = ByteArrayInputStream(byteArray)
                outputStream = FileOutputStream(targetFile)
                while (true) {
                    read = inputStream.read(readBuffer)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(readBuffer,0,read)
                }
                outputStream.flush()
                return@map true
            } catch (e: IOException) {
                e.printStackTrace()
            }finally {
                outputStream!!.close()
                inputStream!!.close()
            }
            false
        }.subscribe {
            if (it) {
                listener.onSuccess(url)
            } else {
                listener.onFail(url)
            }
        }
    }

    fun getFileName(url: String): String {
        var fileName = Uri.parse(url)
        return fileName.pathSegments.last()
    }

    interface Listener {
        fun onSuccess(url: String)
        fun onFail(url: String)
    }
}