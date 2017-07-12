package com.ohoussein.showreader.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */

public class Image {

    @SerializedName("medium")
    private String thumbnail;
    @SerializedName("original")
    private String image;

    public Image() {
    }

    public Image(String thumbnail, String image) {
        this.thumbnail = thumbnail;
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "thumbnail='" + thumbnail + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
