package com.theironyard.charlotte.repositories;

import com.theironyard.charlotte.entities.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

//    List<Person> findByFirstnameOrLastnameOrUsernameAllContainingAllIgnoreCase(String firstName, String lastName, String userName);

//    List<Person> findByFirstNameOrLastNameOrUsernameAllContainingAllIgnoreCase (String firstName, String lastName, String userName);

    List<Person> findByfirstNameContaining(String firstName);

}
