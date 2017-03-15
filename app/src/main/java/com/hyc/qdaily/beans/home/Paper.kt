package com.hyc.qdaily.beans.home

/**
 * Created by ray on 17/3/14.
 */
class Paper {
    /**
     * "last_key":1488906017,
    "has_more":true,
    "feeds":Array[12],
    "feeds_ad":Array[0],
    "paper_topics":Array[1]
     */
    var last_key: Long = 0
    var has_more: Boolean = false
    var feeds: ArrayList<Feed>? = null
    var feeds_ad: ArrayList<Feed>? = null
    var paper_topics: ArrayList<Insert>? = null

}