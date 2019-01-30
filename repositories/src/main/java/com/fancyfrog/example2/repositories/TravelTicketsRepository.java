package com.fancyfrog.example2.repositories;

import com.fancyfrog.example2.model.TravelTickets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TravelTicketsRepository extends CrudRepository<TravelTickets,String>,QueryByExampleExecutor<String> {

}
