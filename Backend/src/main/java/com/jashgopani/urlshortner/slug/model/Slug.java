package com.jashgopani.urlshortner.slug.model;

/**
 * Model for the slug resource
 */
public class Slug {
    private String id;
    private String url;

    public Slug(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Slug [id=" + id + ", url=" + url + "]";
    }
}
