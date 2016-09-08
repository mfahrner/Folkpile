package com.theironyard.charlotte.entities;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue
    int id;

    @Column (nullable = false)
    String firstName;

    @Column (nullable = false)
    String lastName;

    @Column (nullable = false, unique = true)
    String userName;

    @Column (nullable = false)
    String gender;

    @Column (nullable = false)
    String birthday;

    @Column (nullable = false)
    String photo;

    public Person() {
    }

    public Person(String firstName, String lastName, String userName, String gender, String birthday, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.gender = gender;
        this.birthday = birthday;
        this.photo = photo;
    }

}
