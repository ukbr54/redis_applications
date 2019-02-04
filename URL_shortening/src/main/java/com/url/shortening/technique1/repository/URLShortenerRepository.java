package com.url.shortening.technique1.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class URLShortenerRepository {

    private @Autowired RedisTemplate redisTemplate;
    private final String idKey;
    private final String urlKey;

    public URLShortenerRepository(){
        this.idKey = "id";
        this.urlKey = "URL";
    }

    public Long incrementId(){
        Long id = redisTemplate.opsForValue().increment(idKey);
        log.info("Incrementing ID: {}",id );
        return id;
    }

    public void saveUrl(String key,String longUrl){
        log.info("Saving: {} at {}",longUrl,key);
        redisTemplate.opsForHash().put(urlKey,key,longUrl);
    }

    public String getUrl(Long id) throws Exception{
        log.info("Retrieving  at {}", id);
        Optional<Object> url = Optional.of(redisTemplate.opsForHash().get(urlKey, "url:" + id));
        if(url.isPresent()){
            log.info("Retrieved {} at {}",url.get(),id);
            return (String)url.get();
        }else{
            throw new Exception("URL at key" + id + " does not exist");
        }
    }
}
