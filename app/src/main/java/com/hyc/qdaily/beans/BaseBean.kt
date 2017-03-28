package com.hyc.qdaily.beans

/**
 * Created by ray on 17/3/2.
 */

class BaseBean<T> {
    var meta: Meta? = null
    var response: T? = null
    var error: Error? = null
}
