package com.theironyard.charlotte.entities;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    List<Group> groupList;


    public Person() {
    }

    public Person(String firstName, String lastName, String userName, String gender, String birthday, String photo, List<Group> groupList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.gender = gender;
        this.birthday = birthday;
        this.photo = photo;
        this.groupList = groupList;
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
}
