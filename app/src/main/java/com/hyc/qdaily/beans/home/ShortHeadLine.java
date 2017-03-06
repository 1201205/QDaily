package com.hyc.qdaily.beans.home;

import java.util.List;

/**
 * Created by ray on 17/3/2.
 */

public class ShortHeadLine {
    private String title;
    private String description;
    private List<String> keywords;


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public List<String> getKeywords() {
        return keywords;
    }


    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
