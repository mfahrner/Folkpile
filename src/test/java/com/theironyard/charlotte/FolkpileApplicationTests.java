package com.theironyard.charlotte;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.charlotte.entities.Group;
import com.theironyard.charlotte.entities.Person;
import com.theironyard.charlotte.repositories.GroupRepository;
import com.theironyard.charlotte.repositories.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FolkpileApplication.class)
@WebAppConfiguration
public class FolkpileApplicationTests {

	@Autowired
	PersonRepository people;

	@Autowired
	GroupRepository groups;

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

    double groupCount = groups.count() + 1;

    double peopleCount = people.count() + 1;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

	@Test
	public void addPersonToGroups() throws Exception {

		Person person = new Person();
		person.setBirthday("8-10-82");
		person.setFirstName("butts");
		person.setGender("neuter");
		person.setLastName("Yamama");
		person.setPhoto("blah.jpeg");
		person.setUserName("yamamam68694");
		person.setId(2030);

		ObjectMapper mapper = new ObjectMapper();
		String jsonPerson = mapper.writeValueAsString(person);

		Group group = new Group();
		group.setGroupName("blahblah");
		group.setId(47);

		String jsonGroup = mapper.writeValueAsString(group);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/group/{id}/{userId}")
						.content(jsonPerson)
						.content(jsonGroup)
						.contentType("application/jsonGroup/jsonPerson")
		);
        
		Assert.assertEquals(peopleCount, people.count());
		Assert.assertEquals(groupCount, groups.count());
	}
}
