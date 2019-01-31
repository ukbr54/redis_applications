package com.url.shortening.repository;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class URLRepository {

    private final RedisCommands redisCommands;
    private final String idKey;
    private final String urlKey;

    public URLRepository(){
        this.idKey = "id";
        this.urlKey = "URL";
        RedisClient client = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connection = client.connect();
        this.redisCommands = connection.sync();
    }

    public Long incrementId(){
        Long id = redisCommands.incr(idKey);
        log.info("Incrementing ID: {}",id - 1);
        return id - 1;
    }

    public void saveUrl(String key,String longUrl){
        log.info("Saving: {} at {}",longUrl,key);
        redisCommands.hset(urlKey,key,longUrl);
    }

    public String getUrl(Long id) throws Exception{
        log.info("Retrieving  at {}", id);
        Optional<Object> url = Optional.of(redisCommands.hget(urlKey, "url:" + id));
        if(url.isPresent()){
            log.info("Retrieved {} at {}",url.get(),id);
            return (String)url.get();
        }else{
            throw new Exception("URL at key" + id + " does not exist");
        }
    }
}
