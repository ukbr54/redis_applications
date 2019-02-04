package com.url.shortening.technique1.service;

import com.url.shortening.technique1.common.MD5;
import com.url.shortening.technique1.common.IDConverter;
import com.url.shortening.technique1.repository.URLShortenerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class URLShortenerService {

    private final URLShortenerRepository urlShortenerRepository;

    @Autowired
    public URLShortenerService(URLShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    /**
     * Calculate the hash value for a given long URL by calling MD5 hash method
     * 	 and masking the lower bits
     * @param longURL  The given long URL
     * @param repeat Repeat factor (1 is default)
     * @return A 7 digit radix 62 string
     */
    public String shortenURL(String longURL,int repeat){
        String hashedUrl = MD5.getMD5(longURL,repeat);
        log.info("The MD5 hashed url: {}",hashedUrl);

        // Simply mask the 40 least significant bits
        // because 2^40 <= 62^7
        // an MD5 hash code is 128 bits
        // 7 character* 8 bits = 56
        String maskedHashedUrl  = hashedUrl.substring(22);
        log.info("After masking: {}",maskedHashedUrl);

        // convert the hashed value to long integer
        long maskedHashedUrlNumber  = Long.parseLong(maskedHashedUrl, 16);
        log.info("Masked url in number: {}",maskedHashedUrlNumber);

        String uniqueID = IDConverter.INSTANCE.createUniqueID(maskedHashedUrlNumber);
        urlShortenerRepository.saveUrl("url:"+uniqueID,longURL);

        String baseUrl = "http://localhost:8080/";
        String shortenedUrl = baseUrl + uniqueID;

        return  shortenedUrl;
    }

    public String getLongURLFromID(String uniqueID) throws Exception {
        Long dictionaryKey = IDConverter.INSTANCE.getDictionaryKeyFromUniqueID(uniqueID);
        String longURL = urlShortenerRepository.getUrl(dictionaryKey);
        log.info("Converting shortened URL back to {}", longURL);
        return longURL;
    }
}
