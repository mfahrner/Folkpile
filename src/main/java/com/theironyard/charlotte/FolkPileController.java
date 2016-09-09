package com.theironyard.charlotte;

import com.theironyard.charlotte.entities.Group;
import com.theironyard.charlotte.entities.Person;
import com.theironyard.charlotte.repositories.GroupRepository;
import com.theironyard.charlotte.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@RestController
public class FolkPileController {
    @RequestMapping(path = "/")
    @ResponseBody
    String home() {
        return "Hello, Super Awesome Webpage!";
    }

    @Autowired
    PersonRepository people;

    @Autowired
    GroupRepository groups;

    @PostConstruct
       public void init() throws IOException {
        if (people.count() == 0) {
            File f = new File("folkpile.csv");
            Scanner fileScanner = new Scanner(f);

            while (fileScanner.hasNext()) {

                String line = fileScanner.nextLine();
                String[] columns = line.split(",");

                Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
                people.save(person);
            }
        }

        if (groups.count() == 0) {
            String human = "human";
            String mutant = "mutant";
            String cyborg = "cyborg";
            Group humanGroup = new Group(human);
            Group mutantGroup = new Group(mutant);
            Group cyborgGroup = new Group(cyborg);
            groups.save(humanGroup);
            groups.save(mutantGroup);
            groups.save(cyborgGroup);
        }
    }

    @RequestMapping(path = "/people", method = RequestMethod.GET)
    public List<Person> getPeople() {
        return (List<Person>) people.findAll();
    }


    @RequestMapping(path = "/group", method = RequestMethod.GET)
    public List<Group> getGroups() {
        return (List<Group>) groups.findAll();
    }

    @RequestMapping(path = "/group/{id}", method = RequestMethod.GET)
    public Group getGroup(@PathVariable("id") int id) {
        return groups.findOne(id);
    }

    @RequestMapping(path = "/group/{id}", method = RequestMethod.POST)
    public Group addPersonToGroups(@PathVariable("id") int id, Group g, Person p, CrudRepository repo) {
        g.addPersonToGroup(p, groups);
        p.addGroupsToPerson(g, people);
        return groups.findOne(id);
    }

    @RequestMapping(path = "/search?q=", method = RequestMethod.GET)
    public List<Person> searchPeople(@RequestParam("firstName") String firstName) {

//        return people.findByfirstNameOrlastNameOruserNameAllContainingAllIgnoreCase(p);

//        return people.findByFirstNameOrLastNameOrUsernameAllContainingAllIgnoreCase(p, p, p);

        return people.findByfirstNameContaining(firstName);
    }
}