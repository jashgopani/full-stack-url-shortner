package com.jashgopani.urlshortner.slug.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.jashgopani.urlshortner.slug.model.Slug;

/**
 * Class to handle the database operations for the slug resource
 */
@Repository
public class SlugRepository implements ISlugRepository {
    HashMap<String, Slug> db;

    public SlugRepository() {
        db = new HashMap<>();
    }

    @Override
    public Slug delete(String id) {
        if (!db.containsKey(id)) {
            throw new IllegalArgumentException("Slug with id " + id + " does not exist");
        }
        return db.remove(id);
    }

    @Override
    public List<Slug> findAll() {
        return db.values().stream().toList();
    }

    @Override
    public Slug findById(String id) {
        return db.get(id);
    }

    @Override
    public void save(Slug slug) {
        if (db.containsKey(slug.getId())) {
            throw new IllegalArgumentException("Slug with id " + slug.getId() + " already exists");
        }
        db.put(slug.getId(), slug);
    }

    @Override
    public void save(List<Slug> slug) {
        slug.forEach(this::save);
    }

    @Override
    public void update(Slug slug) {
        if (!db.containsKey(slug.getId())) {
            throw new IllegalArgumentException("Slug with id " + slug.getId() + " does not exist");
        }
        db.put(slug.getId(), slug);
    }

}
