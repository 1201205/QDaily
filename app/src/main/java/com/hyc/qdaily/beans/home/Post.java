package com.hyc.qdaily.beans.home;

public class Post {
    private int comment_count;
    private String image;
    private int page_style;
    private String description;
    private String title;
    private int start_time;
    private int post_id;
    private String datatype;
    private int publish_time;
    private int genre;
    private String super_tag;
    private int praise_count;
    private String appview;
    private String film_length;
    private int id;
    private Category category;


    public Column getColumn() {
        return column;
    }


    public void setColumn(Column column) {
        this.column = column;
    }


    private Column column;


    public int getComment_count() {return this.comment_count;}


    public void setComment_count(int comment_count) {this.comment_count = comment_count;}


    public String getImage() {return this.image;}


    public void setImage(String image) {this.image = image;}


    public int getPage_style() {return this.page_style;}


    public void setPage_style(int page_style) {this.page_style = page_style;}


    public String getDescription() {return this.description;}


    public void setDescription(String description) {this.description = description;}


    public String getTitle() {return this.title;}


    public void setTitle(String title) {this.title = title;}


    public int getStart_time() {return this.start_time;}


    public void setStart_time(int start_time) {this.start_time = start_time;}


    public int getPost_id() {return this.post_id;}


    public void setPost_id(int post_id) {this.post_id = post_id;}


    public String getDatatype() {return this.datatype;}


    public void setDatatype(String datatype) {this.datatype = datatype;}


    public int getPublish_time() {return this.publish_time;}


    public void setPublish_time(int publish_time) {this.publish_time = publish_time;}


    public int getGenre() {return this.genre;}


    public void setGenre(int genre) {this.genre = genre;}


    public Object getSuper_tag() {return this.super_tag;}


    public void setSuper_tag(String super_tag) {this.super_tag = super_tag;}


    public int getPraise_count() {return this.praise_count;}


    public void setPraise_count(int praise_count) {this.praise_count = praise_count;}


    public String getAppview() {return this.appview;}


    public void setAppview(String appview) {this.appview = appview;}


    public String getFilm_length() {return this.film_length;}


    public void setFilm_length(String film_length) {this.film_length = film_length;}


    public int getId() {return this.id;}


    public void setId(int id) {this.id = id;}


    public Category getCategory() {return this.category;}


    public void setCategory(Category category) {this.category = category;}
}
