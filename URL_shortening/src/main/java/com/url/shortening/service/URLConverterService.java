package com.url.shortening.service;

import com.url.shortening.common.IDConverter;
import com.url.shortening.repository.URLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class URLConverterService {

    private final URLRepository urlRepository;

    @Autowired
    public URLConverterService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenURL(String localURL,String longURL){
        log.info("Shortening {}", longURL);
        Long id = urlRepository.incrementId();
        String uniqueID = IDConverter.INSTANCE.createUniqueID(id);
        urlRepository.saveUrl("url:"+id,longURL);
        String baseUrl = "http://localhost:8080/";
        String shortenedUrl = baseUrl + uniqueID;
        return  shortenedUrl;
    }
}
