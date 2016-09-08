package com.theironyard.charlotte.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue
    Integer id;

    String firstName;
    String lastName;
    String userName;
    String gender;
    String birthday;
    String photo;

    public Person(String firstName, String lastName, String userName, String gender, String birthday, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.gender = gender;
        this.birthday = birthday;
        this.photo = photo;
    }
}
