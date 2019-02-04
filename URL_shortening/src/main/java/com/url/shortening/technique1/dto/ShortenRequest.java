package com.url.shortening.technique1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShortenRequest {

    private String url;

    @JsonCreator
    public ShortenRequest(){}

    @JsonCreator
    public ShortenRequest(@JsonProperty("url") String url){
        this.url = url;
    }
}
