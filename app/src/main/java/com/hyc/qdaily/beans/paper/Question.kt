package com.hyc.qdaily.beans.paper

/**
 * Created by ray on 17/3/27.
 */
class Question {
    /**
     *  "id":2051,
    "title":"在生活中，哪些钱是你不知不觉花掉的？",
    "content":"在生活中，你通常会不知不觉花掉哪些钱？",
    "genre":2,  1->1选4  2->单选   3->滑动选择  4—>性别选择
    "options":Array[8]
     */
    var image: String? = null
    var id: String? = null
    var title: String? = null
    var content: String? = null
    var genre: Int = 0
    var options: ArrayList<Option>? = null
    var max_score: Int = 0
}