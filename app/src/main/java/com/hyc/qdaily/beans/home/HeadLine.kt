package com.hyc.qdaily.beans.home

/**
 * Created by ray on 17/3/2.
 */

class HeadLine {
    /**
     * "image":"http://img.qdaily.com/article/article_show/201703020815132JNlkCsTp4rFwh8t.jpg?imageMogr2/auto-orient/thumbnail/!640x380r/gravity/Center/crop/640x380/quality/85/format/jpg/ignore-error/1",
     * "type":1,
     * "post":Object{...},
     * "list":Array[3],
     * "headline_genre":1
     */
    var image: String? = null
    var type: Int = 0
    var post: Post? = null
    var list: List<ShortHeadLine>? = null
    var headline_genre: Int = 0
}
