package com.url.shortening.technique1.service;

import com.url.shortening.technique1.common.MD5;
import com.url.shortening.technique1.common.IDConverter;
import com.url.shortening.technique1.repository.URLShortenerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class URLShortenerService {

    private final URLShortenerRepository urlShortenerRepository;

    @Autowired
    public URLShortenerService(URLShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    /**
     * Tries to shorten the given long URL. If the corresponding short URL
     * exists and is associated with different long URL, the process recursively
     * continues until finding a unique short URL
     *
     * @param url
     *            The given long URL to be shorten
     * @param repeat
     *            The repeat factor which is 1 by defaults
     * @return The shortened URL
     */
    public String shortenURL(String url,int repeat) throws Exception{
        String baseUrl = "http://localhost:8080/";
        StringBuilder shortenedUrl = new StringBuilder(baseUrl);
        String shortUrl = getUrlHashedValue(url, repeat);
        Optional<String> longUrl = Optional.ofNullable(getLongURLFromID(shortUrl));
        if(!(longUrl.isPresent())){
            urlShortenerRepository.saveUrl("url:"+shortUrl,url);
        }else if(longUrl.isPresent() && url.equals(longUrl.get())){
            return shortenedUrl.append(shortUrl).toString();
        }else{
            shortUrl = shortenURL(url,repeat++);
        }
        return shortenedUrl.append(shortUrl).toString();
    }

    /**
     * Calculate the hash value for a given long URL by calling MD5 hash method
     * 	 and masking the lower bits
     * @param longUrl  The given long URL
     * @param repeat Repeat factor (1 is default)
     * @return A 7 digit radix 62 string
     */
    private String getUrlHashedValue(String longUrl,int repeat){
        String hashedUrl = MD5.getMD5(longUrl,repeat);
        log.info("The MD5 hashed url: {}",hashedUrl);

        // Simply mask the 40 least significant bits because 2^40 <= 62^7
        // an MD5 hash code is 128 bits
        // 7 character* 8 bits = 56
        String maskedHashedUrl  = hashedUrl.substring(22);
        log.info("After masking: {}",maskedHashedUrl);

        // convert the hashed value to long integer
        long maskedHashedUrlNumber  = Long.parseLong(maskedHashedUrl, 16);
        log.info("Masked url in number: {}",maskedHashedUrlNumber);

        String uniqueID = IDConverter.INSTANCE.createUniqueID(maskedHashedUrlNumber);
        return uniqueID;
    }

    public String getLongURLFromID(String uniqueID) {
        log.info("Finding the Long url for: {}",uniqueID);
        //Long dictionaryKey = IDConverter.INSTANCE.getDictionaryKeyFromUniqueID(uniqueID);
        String longURL = urlShortenerRepository.getUrl(uniqueID);
        log.info("Converting shortened URL back to {}", longURL);
        if(longURL != null){
            return longURL;
        }else{
            throw new RuntimeException("Key is "+uniqueID+" is not exist");
        }
    }
}
