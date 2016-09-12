package com.theironyard.charlotte.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "groups")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
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

    public Group(Group otherGroup) {
        id = otherGroup.id;
        groupName = otherGroup.groupName;
        people = otherGroup.people;
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

//    @JsonIgnore
    public List<Person> getPeople() {
        return people;
    }
//
//    public List<Integer> getPeopleIds() {
//        return getPeople().stream().map(Person::getId).collect(Collectors.toList());
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
