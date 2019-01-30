package com.fancyfrog.example2.ws;

import com.fancyfrog.example2.model.CityDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class WSTravelTickets {

    private String city;
    private String continent;
    private String region;
    private String IATACode;
    private List<WSTicket> tickets;

    public WSTravelTickets(CityDetails cityDetails){
        this.city  = cityDetails.getCity();
        this.continent = cityDetails.getContinent();
        this.IATACode = cityDetails.getIATACode();
        this.region = cityDetails.getRegion();
        this.tickets = cityDetails.getTickets().stream().map(WSTicket::new).collect(Collectors.toList());
    }
}
