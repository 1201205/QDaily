package com.hyc.qdaily.beans.home;

import java.util.List;

/**
 * Created by ray on 17/3/3.
 */

public class ColumnContent {
    /**
     * "last_key":1487848355,
     "has_more":true,
     "feeds":Array[12]
     */
    private String last_key;


    public String getLast_key() {
        return last_key;
    }


    public void setLast_key(String last_key) {
        this.last_key = last_key;
    }


    public boolean isHas_more() {
        return has_more;
    }


    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }


    public List<Feed> getFeeds() {
        return feeds;
    }


    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }


    private boolean has_more;
    private List<Feed> feeds;
}
