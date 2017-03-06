package com.hyc.qdaily.beans.home;

import java.util.List;

/**
 * Created by ray on 17/3/3.
 */

public class ArticleContent {
    /**
     *  "js":Array[3],
     "css":Array[2],
     "image":Array[17]
     */
    private int id;
    private List<String> js;
    private List<String> css;
    private List<String> image;
    private String body;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public List<String> getJs() {
        return js;
    }


    public void setJs(List<String> js) {
        this.js = js;
    }


    public List<String> getCss() {
        return css;
    }


    public void setCss(List<String> css) {
        this.css = css;
    }


    public List<String> getImage() {
        return image;
    }


    public void setImage(List<String> image) {
        this.image = image;
    }


    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }
}
