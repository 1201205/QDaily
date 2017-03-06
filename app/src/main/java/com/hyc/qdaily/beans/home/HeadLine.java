package com.hyc.qdaily.beans.home;

import java.util.List;

/**
 * Created by ray on 17/3/2.
 */

public class HeadLine {
    /**
     *  "image":"http://img.qdaily.com/article/article_show/201703020815132JNlkCsTp4rFwh8t.jpg?imageMogr2/auto-orient/thumbnail/!640x380r/gravity/Center/crop/640x380/quality/85/format/jpg/ignore-error/1",
     "type":1,
     "post":Object{...},
     "list":Array[3],
     "headline_genre":1
     */
    private String image;
    private int type;
    private Post post;
    private List<ShortHeadLine> list;
    private int headline_genre;


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }


    public Post getPost() {
        return post;
    }


    public void setPost(Post post) {
        this.post = post;
    }


    public List<ShortHeadLine> getList() {
        return list;
    }


    public void setList(List<ShortHeadLine> list) {
        this.list = list;
    }


    public int getHeadline_genre() {
        return headline_genre;
    }


    public void setHeadline_genre(int headline_genre) {
        this.headline_genre = headline_genre;
    }
}
