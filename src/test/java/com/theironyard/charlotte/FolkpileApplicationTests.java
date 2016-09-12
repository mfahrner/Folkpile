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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

	@Test
    @Transactional
	public void addPersonToGroups() throws Exception {
		Person p = people.findOne(1);

		int groupCount = p.getGroups().size();

		mockMvc.perform(
				MockMvcRequestBuilders.post("/group/{id}/{userId}", "1", "1")
		);

		Assert.assertEquals(groupCount + 1, p.getGroups().size());
	}
}
