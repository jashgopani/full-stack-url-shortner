package com.jashgopani.urlshortner.slug.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jashgopani.urlshortner.slug.model.Slug;
import com.jashgopani.urlshortner.slug.repository.SlugRepository;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;

/**
 * Class to handle the business logic for the slug resource
 */
@Service
public class SlugService {

    @Autowired
    private SlugRepository slugRepository;

    @Value("${slug.length}")
    private int slugLength;

    /**
     * Method to generate a random slug
     * 
     * @return a random slug
     * @throws IOException
     * @throws MalformedURLException
     */
    public Slug generateSlug(String url) throws MalformedURLException, IOException {
        new URL(url).openConnection().connect();// this will throw an error is URL is invalid

        String id = NanoIdUtils.randomNanoId(slugLength);
        Slug slug = new Slug(id, url);
        slugRepository.save(slug);
        return slug;
    }

    /**
     * Method to get a slug by its id
     * 
     * @param id the id of the slug
     * @return the slug with the given id
     */
    public Slug getSlug(String id) {
        return slugRepository.findById(id);
    }

    
    public List<Slug> findAll() {
        return slugRepository.findAll();
    }

}
