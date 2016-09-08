package com.theironyard.charlotte;

import com.theironyard.charlotte.entities.Person;
import com.theironyard.charlotte.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Ben on 9/8/16.
 */
@Controller
public class FolkPileController {
    @RequestMapping(path = "/")
    @ResponseBody
    String home() {
        return "Hello, World!";
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

                Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
                people.save(person);
            }
        }
    }
}
