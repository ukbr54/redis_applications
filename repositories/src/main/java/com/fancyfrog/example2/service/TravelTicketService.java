package com.fancyfrog.example2.service;

import com.fancyfrog.csv.CsvService;
import com.fancyfrog.example2.model.CityDetails;
import com.fancyfrog.example2.model.CountryDetails;
import com.fancyfrog.example2.model.TravelTickets;
import com.fancyfrog.example2.repositories.CityDetailsRepository;
import com.fancyfrog.example2.repositories.CountryDetailsRepository;
import com.fancyfrog.example2.repositories.TravelTicketsRepository;
import com.fancyfrog.example2.vo.TicketVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TravelTicketService {

   private @Autowired CsvService csvService;
   private @Autowired TravelTicketsRepository travelTicketsRepository;
   private @Autowired CityDetailsRepository cityDetailsRepository;
   private @Autowired CountryDetailsRepository countryDetailsRepository;

   public void dumpCsvFileIntoRedisServer(String fileName){
       List<TicketVO> ticketData = csvService.readCsvFile(TicketVO.class, fileName);
       Map<String, List<TicketVO>> groupByCountry = ticketData.stream().collect(Collectors.groupingBy(TicketVO::getCountry));
       for(Map.Entry<String,List<TicketVO>> map : groupByCountry.entrySet()){
           List<CityDetails> savedCities = new ArrayList<>();
           Map<String, List<TicketVO>> groupByCity = map.getValue().stream().collect(Collectors.groupingBy(TicketVO::getCity));

           for(Map.Entry<String,List<TicketVO>> secondMap : groupByCity.entrySet()){
               List<TravelTickets> tickets = secondMap.getValue().stream().map(TravelTickets::new).collect(Collectors.toList());
               List<TravelTickets> savedTickets =
                       tickets.stream().map(ticket -> travelTicketsRepository.save(ticket)).collect(Collectors.toList());

               if(secondMap.getKey().matches("[a-zA-Z]+")){
                   CityDetails cityDetails = new CityDetails();
                   cityDetails.setCity(secondMap.getKey());
                   cityDetails.setContinent(secondMap.getValue().stream().findFirst().get().getContinent());
                   cityDetails.setRegion(secondMap.getValue().stream().findFirst().get().getRegion());
                   cityDetails.setIATACode(secondMap.getValue().stream().findFirst().get().getIATACode());
                   cityDetails.setTickets(savedTickets);
                   cityDetailsRepository.save(cityDetails);
                   savedCities.add(cityDetails);
               }
           }

           CountryDetails countryDetails = new CountryDetails();
           countryDetails.setCountry(map.getKey());
           countryDetails.setCities(savedCities);
           countryDetailsRepository.save(countryDetails);
       }
   }

   public CityDetails getCityTickets(String city){
       long start = System.currentTimeMillis();
       Optional<CityDetails> cityDetails = cityDetailsRepository.findById(city);
       log.info("Retrieving the city data from redis server: "+(System.currentTimeMillis() - start)+" ms");
       if(cityDetails.isPresent()){
           return cityDetails.get();
       }else{
           throw new RuntimeException("City is not present");
       }
   }

   public CountryDetails getCountryTickets(String country){
       long start = System.currentTimeMillis();
       Optional<CountryDetails> countryDetails = countryDetailsRepository.findById(country);
       log.info("Retrieving the country data from redis server: "+(System.currentTimeMillis() - start)+" ms");
       if(countryDetails.isPresent()){
           return countryDetails.get();
       }else{
           throw new RuntimeException("Country is not present");
       }
   }
}
