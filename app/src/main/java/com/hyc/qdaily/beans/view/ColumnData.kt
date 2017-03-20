package com.hyc.qdaily.beans.view

/**
 * Created by ray on 17/3/3.
 */

class ColumnData {
    /**
     * "last_key":1487848355,
     * "has_more":true,
     * "feeds":Array[12]
     */
    var name: String? = null
    var icon: String? = null
    var feeds: ArrayList<ViewData>? = null
    var index: Int? = 0
    var id: String? = null
    var showType: Int = 0
    var lastIndex: String? = "0"
    var requesting: Boolean = false


}
