package com.url.shortening.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class URLController {

    @GetMapping(value = "/shortener")
    public String shortenUrl(HttpServletRequest request){
        String toString = request.getRequestURL().toString();
        return "Hello World";
    }
}
