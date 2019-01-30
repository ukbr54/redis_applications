package com.fancyfrog.example2.ws;

import com.fancyfrog.example2.model.CityDetails;
import com.fancyfrog.example2.model.CountryDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class WSTravelTickets {

    private String country;
    private String city;
    private String continent;
    private String region;
    private String IATACode;
    private List<WSTicket> tickets = new ArrayList<>();

    public WSTravelTickets(CityDetails cityDetails){
        this.city  = cityDetails.getCity();
        this.continent = cityDetails.getContinent();
        this.IATACode = cityDetails.getIATACode();
        this.region = cityDetails.getRegion();
        this.tickets = cityDetails.getTickets().stream().map(WSTicket::new).collect(Collectors.toList());
    }

    public WSTravelTickets(CountryDetails countryDetails){
        this.country = countryDetails.getCountry();
        CityDetails cityDetails = countryDetails.getCities().stream().findFirst().get();
        this.continent = cityDetails.getContinent();
        this.region = cityDetails.getRegion();
        this.IATACode = cityDetails.getIATACode();
        List<CityDetails> cities = countryDetails.getCities();
        cities.forEach(city -> this.tickets.addAll(city.getTickets().stream().map(WSTicket::new).collect(Collectors.toList())));
    }
}
