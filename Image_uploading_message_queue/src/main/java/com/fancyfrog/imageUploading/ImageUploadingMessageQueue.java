package com.fancyfrog.imageUploading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration
public class ImageUploadingMessageQueue {

    public static void main(String[] args) {
        log.debug("ImageUploadingMessageQueue application is starting ");
       try{
           SpringApplication.run(ImageUploadingMessageQueue.class,args);
       }catch (Exception e){
           log.error("Error occured while starting ImageUploadingMessageQueue Application");
       }
       log.debug("ImageUploadingMessageQueue application is started");
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver(){
      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
      multipartResolver.setMaxUploadSize(-1);
      return multipartResolver;
    }
}
