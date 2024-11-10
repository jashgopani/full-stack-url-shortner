package com.jashgopani.urlshortner.slug.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class SlugController {

    @GetMapping("/")
    public String index() {
        return "URL Shortner is running!";
    }

}
