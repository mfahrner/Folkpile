package com.theironyard.charlotte.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "people")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(mappedBy = "people")
    List<Group> groups = new ArrayList<>();

    public Person() {
    }

    public Person(Person otherPerson) {
        id = otherPerson.id;
        firstName = otherPerson.firstName;
        lastName = otherPerson.lastName;
        userName = otherPerson.userName;
        gender = otherPerson.gender;
        birthday = otherPerson.birthday;
        photo = otherPerson.photo;
        groups = otherPerson.groups;
    }

    public Person(List<Group> groups) {
        this.groups = groups;
    }

    public Person(String firstName, String lastName, String userName, String gender, String birthday, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.gender = gender;
        this.birthday = birthday;
        this.photo = photo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Group> getGroups() {
        return groups;
    }

//    Had to go a different route bc this also broke front end code
//    Wanted to leave it commented out as an example of stream for future ref
//    public List<Integer> getGroupIds() {
//        return getGroups().stream().map(Group::getId).collect(Collectors.toList());
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person addGroupsToPerson(Group g, CrudRepository repo) {
        groups.add(g);
        g.people.add(this);

        repo.save(this);
        return this;
    }
}
