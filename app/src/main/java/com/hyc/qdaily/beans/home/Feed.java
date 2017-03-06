package com.hyc.qdaily.beans.home;

/**
 * Created by ray on 17/3/2.
 */

public class Feed {
    /**
     *   "image":"http://img.qdaily.com/article/article_show/201703021239208kwOTPH2hqIbWZ5Q.gif?imageMogr2/auto-orient/thumbnail/!640x380r/gravity/Center/crop/640x380/quality/85/ignore-error/1",
     "type":1,
     "post":Object{...}
     "advertisement":Object{...},
     "cover":Object{...}
     */
    private String image;
    private int type;//1为普通的文字图片混排（图片在右 文字在左）  0，2为lib形式图片在上面 文字在下  0有好奇研究所这个文字
    private Post post;
    private Advertisement advertisement;
    private Cover cover;


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


    public Advertisement getAdvertisement() {
        return advertisement;
    }


    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }


    public Cover getCover() {
        return cover;
    }


    public void setCover(Cover cover) {
        this.cover = cover;
    }
}
