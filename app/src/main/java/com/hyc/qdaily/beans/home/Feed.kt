package com.hyc.qdaily.beans.home

/**
 * Created by ray on 17/3/2.
 */

class Feed {
    /**
     * "image":"http://img.qdaily.com/article/article_show/201703021239208kwOTPH2hqIbWZ5Q.gif?imageMogr2/auto-orient/thumbnail/!640x380r/gravity/Center/crop/640x380/quality/85/ignore-error/1",
     * "type":1,
     * "post":Object{...}
     * "advertisement":Object{...},
     * "cover":Object{...}
     */
    var image: String? = null
    var type: Int = 0//1为普通的文字图片混排（图片在右 文字在左）  0，2为lib形式图片在上面 文字在下  0有好奇研究所这个文字
    var post: Post? = null
    var advertisement: Advertisement? = null
    var cover: Cover? = null
}
