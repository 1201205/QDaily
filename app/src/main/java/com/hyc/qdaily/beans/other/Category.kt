package com.hyc.qdaily.beans.other

import com.hyc.qdaily.beans.home.Feed

/**
 * Created by ray on 17/3/26.
 */
class Category {
    /**
     *  "last_key":1489747780,
    "has_more":true,
    "feeds":Array[12],
    "feeds_ad":Array[0]
     */

    var last_key: String? = null
    var has_more: Boolean = true
    var feeds: ArrayList<Feed>? = null
    var feeds_ad: ArrayList<Feed>? = null
}