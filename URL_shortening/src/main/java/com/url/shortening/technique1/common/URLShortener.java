package com.url.shortening.technique1.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;

public class URLShortener {

    private @Autowired RedisTemplate redisTemplate;
    private final char myChars[]; // This array is used for character to number mapping
    private final int keyLength; // the key length in URL defaults to 8
    private final Random random; //Random object used to generate random integers
    private final String urlKey;


    URLShortener(){
        myChars = new char[62];
        keyLength = 8;
        random = new Random();
        urlKey = "URL";
        for(int i = 0; i < 62; i++){
            int j = 0;
            if(j < 10){
                j = i + 48;
            }else if(i > 9 && i < 35){
                j = i + 55;
            }else{
                j = i + 61;
            }
            myChars[i] = (char)j;
        }
    }

    // the public method which can be called to shorten a given URL
    public String shortenUrl(String longUrl){
        StringBuilder shortUrl = new StringBuilder();
        longUrl = sanitizeURL(longUrl);
        return null;
    }

    // This method should take care various issues with a valid url
    // e.g. www.google.com,www.google.com/, http://www.google.com,http://www.google.com/
    // all the above URL should point to same shortened URL
    // There could be several other cases like these.
    private String sanitizeURL(String url){
        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        if (url.substring(0, 8).equals("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

    private String generateKey(){
        int counter = 0;
        String key = "";
        boolean flag = true;
        while (flag) {
            key = "";
            for (int i = 0; i <= keyLength; i++) {
                int digit = random.nextInt(62);
                key += myChars[digit];

            }
            System.out.println("Iteration: "+ counter + "Key: "+ key);
            //we are using -- use putIfAbsent return boolean
            Boolean hello = redisTemplate.opsForHash().putIfAbsent(this.urlKey, key, "Hello");
            if(redisTemplate.opsForHash().hasKey(this.urlKey,key)){
                flag = false;
            }
            counter ++;
        }
        return key;
    }

    public static void main(String[] args) {
        URLShortener shortener = new URLShortener();

    }
}
