package com.fancyfrog.example2.resource;

import com.fancyfrog.example2.model.CityDetails;
import com.fancyfrog.example2.model.CountryDetails;
import com.fancyfrog.example2.service.TravelTicketService;
import com.fancyfrog.example2.ws.WSTravelTickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private @Autowired TravelTicketService travelTicketService;

    @GetMapping("/dump")
    public ResponseEntity<?> storeDataInRedis(){
        travelTicketService.dumpCsvFileIntoRedisServer("C:\\Users\\QuaQUa\\Desktop\\vapProducts.csv");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<?> getCityTickets(@RequestParam("city") String city){
        CityDetails cityTickets = travelTicketService.getCityTickets(city);
        WSTravelTickets travelTickets = new WSTravelTickets(cityTickets);
        return new ResponseEntity<>(travelTickets,HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity<?> getCountryTickets(@RequestParam("country") String country){
        CountryDetails countryTickets = travelTicketService.getCountryTickets(country);
        WSTravelTickets travelTickets = new WSTravelTickets(countryTickets);
        return new ResponseEntity<>(travelTickets,HttpStatus.OK);
    }
}
