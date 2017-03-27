package com.hyc.qdaily.beans.paper

import com.hyc.qdaily.beans.home.Paper
import com.hyc.qdaily.beans.home.Post
import com.hyc.qdaily.beans.home.Share

/**
 * Created by hyc on 17/3/27.
 */
class PaperDetail {
    /**
     * "image":"http://img.qdaily.com/paper/paper_show/20170324121648z7d1Rgb5HaSnNAxr.jpg?imageMogr2/auto-orient/thumbnail/!1030x430r/gravity/Center/crop/1030x430/quality/85/format/jpg/ignore-error/1",
    "type":0,
    "post":Object{...},
    "options":Array[50],
    "share":Object{...}


    "paper":Object{...},
    "share":Object{...},
    "normal_questions":Array[11]
     */
    var image: String? = null
    var type: Int = 0
    var post: Post? = null
    var share: Share? = null
    var options: ArrayList<Option>? = null
    var questions: ArrayList<Question>? = null
    var paper: Paper? = null
    var normal_questions: ArrayList<Question>? = null

}