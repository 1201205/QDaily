package com.hyc.qdaily.beans.paper

/**
 * Created by hyc on 17/3/27.
 */
class Option {
    /**
     *  "id":184576,
    "content":"道德绑架",
    "image":"",
    "praise_count":0,
    "perfect":0,
    "author":Object{...}

    "id":179293,
    "title":"《铁达尼号》",
    "option_pic_url":"",
    "score":1
     */
    var id: String? = null
    var content: String? = null
    var image: String? = null
    var title: String? = null
    var option_pic_url: String? = null
    var score: Int = 0
    var praise_count: Int = 0
    var perfect: Int = 0
    var author: Author? = null
    var selected: Boolean = false
    var collapsed: Boolean = true


}