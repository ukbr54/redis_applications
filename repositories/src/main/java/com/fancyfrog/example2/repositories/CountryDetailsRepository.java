package com.fancyfrog.example2.repositories;

import com.fancyfrog.example2.model.CountryDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CountryDetailsRepository extends CrudRepository<CountryDetails,String>,QueryByExampleExecutor<CountryDetails> {
}
