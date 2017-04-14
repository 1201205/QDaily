package com.hyc.qdaily.beans.paper

import com.hyc.qdaily.beans.home.Post
import com.hyc.qdaily.beans.home.Share

/**
 * Created by hyc on 2017/4/14.
 */
class Tot {
    /**
     * "paper":Object{...},
    "share":Object{...},
    "gender_question":Object{...},
    "slide_question":Object{...},
    "normal_questions":Array[9]
     */
    var paper: Post? = null
    var share: Share?? = null
    var gender_question: Question? = null
    var slide_question: Question? = null
    var normal_questions: ArrayList<Question>? = null
}