package com.theironyard.charlotte.entities;


import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column (nullable = false, unique = true)
    String groupName;

    @ManyToMany
    @JoinTable
    List<Person> people = new ArrayList<>();

    public Group() {

    }

    public Group(String groupName) {
        this.groupName = groupName;

    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Group addPersonToGroup(Person p, CrudRepository repo) {
        people.add(p);
        p.groups.add(this);

        repo.save(this);
        return this;
    }

    public List<Person> getPeople() {
        return people;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
