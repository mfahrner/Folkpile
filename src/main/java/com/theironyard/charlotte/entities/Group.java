package com.theironyard.charlotte.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue
    Integer id;

    @Column (nullable = false, unique = true)
    String groupName;

    @ManyToMany
    List<Person> personList;

    public Group(String groupName, List<Person> personList) {
        this.groupName = groupName;
        this.personList = personList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
