package com.theironyard.charlotte.repositories;

import com.theironyard.charlotte.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE p.firstName LIKE %?1%")
    List<Person> findByNameStartsWith(String firstName);

}
