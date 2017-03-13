package com.hyc.qdaily.beans.view

import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.home.HeadLine

/**
 * Created by ray on 17/3/8.
 */
class ViewData {
    var banners: ArrayList<Feed>? = null
    var type: String = ""
    var feed: Feed? = null
    var columnContent: ColumnData? = null
    var headLine: HeadLine? = null
}