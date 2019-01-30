package com.fancyfrog.example2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@RedisHash("countryDetails")
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "cities" })
public class CountryDetails {

    private @Id String country;
    private @Reference List<CityDetails> cities;
}
