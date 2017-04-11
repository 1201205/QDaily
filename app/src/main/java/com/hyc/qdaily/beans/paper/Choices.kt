package com.hyc.qdaily.beans.paper

import com.hyc.qdaily.beans.home.Paper
import com.hyc.qdaily.beans.home.Post
import com.hyc.qdaily.beans.home.Share

/**
 * Created by hyc on 2017/4/10.
 */
class Choices {
    /**
     *  "paper":Object{...},
    "share":Object{...},
    "normal_questions":Array[9]
     */
    var paper: Post? = null
    var share: Share?? = null
    var normal_questions: ArrayList<Question>? = null
}