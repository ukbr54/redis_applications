package com.url.shortening.controller;

import com.url.shortening.common.URLValidator;
import com.url.shortening.dto.ShortenRequest;
import com.url.shortening.service.URLConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
public class URLController {

    private final URLConverterService urlConverterService;

    @Autowired
    public URLController(URLConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }

    @PostMapping(value = "/shortener",consumes = { "application/json" })
    public String shortenUrl(@RequestBody @Valid final ShortenRequest shortenRequest, HttpServletRequest request) throws Exception{
        log.info("Received URL to shorten: "+shortenRequest.getUrl());
        String longUrl = shortenRequest.getUrl();
        if(URLValidator.INSTANCE.validateURL(longUrl)){
            String shortenedUrl = urlConverterService.shortenURL(null,longUrl);
            log.info("Shortened url to: " + shortenedUrl);
            return shortenedUrl;
        }
        throw new Exception("Please enter a valid URL");
    }

    @GetMapping(value = "/{id}")
    public RedirectView redirectUrl(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Received shorten url to redirect: "+id);
        String redirectUrlString  = urlConverterService.getLongURLFromID(id);
        log.info("Original URL: "+redirectUrlString);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrlString);
        return redirectView;
    }
}
