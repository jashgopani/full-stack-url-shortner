package com.jashgopani.urlshortner.slug.model;

/**
 * Model for the slug resource
 */
public class Slug {
    private String slug;
    private String url;

    public Slug() {
    }

    public Slug(String slug, String url) {
        this.slug = slug;
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
