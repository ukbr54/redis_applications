package com.fancyfrog.example2.repositories;

import com.fancyfrog.example2.model.CityDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CityDetailsRepository extends CrudRepository<CityDetails,String>,QueryByExampleExecutor<CityDetails> {

}
