package com.fancyfrog.example2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@RedisHash("cityDetails")
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "tickets" })
public class CityDetails {

    private @Id String city;
    private String continent;
    private String region;
    private String IATACode;
    private @Reference List<TravelTickets> tickets;
}
