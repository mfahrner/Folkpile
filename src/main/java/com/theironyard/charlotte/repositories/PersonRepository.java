package com.theironyard.charlotte.repositories;

import com.theironyard.charlotte.entities.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Blake on 9/8/16.
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {

}
