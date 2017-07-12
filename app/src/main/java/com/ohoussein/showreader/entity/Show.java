package com.ohoussein.showreader.entity;

/**
 * Created by Houssein OUERGHEMMI on 12/07/17.
 * ouerghemmi.houssein@gmail.com
 */

public class Show {

    private long id;
    private String name;
    private String summary;
    private String url;

    private Image image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", url='" + url + '\'' +
                ", image=" + image +
                '}';
    }
}
