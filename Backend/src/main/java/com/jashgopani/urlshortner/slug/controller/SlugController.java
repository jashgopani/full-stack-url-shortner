package com.jashgopani.urlshortner.slug.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.jashgopani.urlshortner.slug.model.Slug;
import com.jashgopani.urlshortner.slug.service.SlugService;
import com.jashgopani.urlshortner.slug.utils.SlugConstants;

import jakarta.servlet.http.HttpServletResponse;

import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SlugController {

    @Autowired
    private SlugService slugService;

    @GetMapping("/")
    public String index() {
        return "URL Shortner is running!";
    }

    @PostMapping("/")
    public ResponseEntity<?> shortenURL(@RequestParam("url") String url) {
        try {
            Slug slug = slugService.generateSlug(url);
            return ResponseEntity.status(HttpStatus.CREATED).body(slug);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorMessage(SlugConstants.ERROR_INVALID_URL));
        }
    }

    @GetMapping("/{slug}")
    public RedirectView redirectToURL(@PathVariable String slug) {
        try {
            Slug slugObj = slugService.getSlug(slug);
            if (Objects.isNull(slugObj)) {
                throw new NullPointerException();
            }
            return new RedirectView(slugObj.getUrl());
        } catch (Exception e) {

            RedirectView redirectView = new RedirectView("/error");
            redirectView.setStatusCode(HttpStatus.NOT_FOUND);
            return redirectView;
        }

    }

    @GetMapping("/slugs")
    public List<Slug> findAllSlugs() {
        return slugService.findAll();
    }

    private Map<String, String> getErrorMessage(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return error;
    }

}
