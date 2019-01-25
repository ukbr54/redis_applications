package com.fancyfrog.imageUploading.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file-upload")
public class ImageUploadingResource {

    @GetMapping
    public ResponseEntity<?> hello(){
        return new ResponseEntity<>("Hello world",HttpStatus.OK);
    }
}
