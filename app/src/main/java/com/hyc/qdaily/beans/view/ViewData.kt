package com.hyc.qdaily.beans.view

import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.home.HeadLine
import com.hyc.qdaily.beans.home.Insert
import com.hyc.qdaily.beans.home.InsertContent

/**
 * Created by ray on 17/3/8.
 */
class ViewData {
    var banners: ArrayList<Feed>? = null
    var type: String = ""
    var feed: Feed? = null
    var columnContent: ColumnData? = null
    var headLine: HeadLine? = null
    var inserts: ArrayList<ViewData>? = null
    var insertContent: InsertContent?=null
}