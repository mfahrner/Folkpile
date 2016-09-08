package com.theironyard.charlotte.repositories;

import com.theironyard.charlotte.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
