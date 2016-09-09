package com.theironyard.charlotte;

import com.theironyard.charlotte.entities.Person;
import com.theironyard.charlotte.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostConstruct
       public void init() throws IOException {
        if (people.count() == 0) {
            File f = new File("folkpile.csv");
            Scanner fileScanner = new Scanner(f);

            while (fileScanner.hasNext()) {

                String line = fileScanner.nextLine();
                String[] columns = line.split(",");

                Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], null);
                people.save(person);
            }
        }
    }

    @RequestMapping(path = "/people", method = RequestMethod.GET)
    public List<Person> getPeople() {
        return (List<Person>) people.findAll();
    }


//    @RequestMapping(path = "/group", method = RequestMethod.GET)
//    public List<Group> getGroups() {
//        return (List<Group>) groups.findAll();
//    }
//
//    @RequestMapping(path = "/group/{id}", method = RequestMethod.GET)
//    public Group getGroup(@PathVariable("id") int id) {
//        return groups.findOne(id);
//    }
}