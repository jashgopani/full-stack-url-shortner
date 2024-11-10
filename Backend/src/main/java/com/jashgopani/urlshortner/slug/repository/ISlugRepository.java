package com.jashgopani.urlshortner.slug.repository;

import java.util.List;

import com.jashgopani.urlshortner.slug.model.Slug;

public interface ISlugRepository {

    public Slug findById(String id);

    public List<Slug> findAll();

    public void save(Slug slug);

    public void save(List<Slug> slug);

    public Slug delete(String id);

    public void update(Slug slug);
}
