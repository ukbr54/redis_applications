package com.fancyfrog.example1.repositories;

import com.fancyfrog.example1.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person,String>,QueryByExampleExecutor<String> {

    List<Person> findByLastName(String lastName);

    Page<Person> findPersonByLastName(String lastName, Pageable page);

    List<Person> findByFirstNameAndLastName(String firstName,String lastName);

    List<Person> findByFirstNameOrLastName(String firstName,String lastName);

    List<Person> findByAddress_City(String city);

    List<Person> findByAddress_LocationWithin(Circle circle);
}
