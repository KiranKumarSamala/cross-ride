/**
 * 
 */
package com.crossover.techtrial.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.service.PersonService;

/**
 * @author Kiran Kumar Samala
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonControllerTest {
  
  @Autowired
  MockMvc mockMvc;
  
  @MockBean
  PersonService personService;
  
	@Test
	public void testGetAllPersons() throws Exception {
		String json = "";
		List<Person> personList = new ArrayList();
		Person person = new Person();
		person.setId(1L);
		personList.add(person);
		Mockito.when(personService.getAll()).thenReturn(personList);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/person").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(json)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
  
	@Test
	public void testGetRideById() throws Exception {
		String json = "";
		Person person = new Person();
		person.setId(1L);
		Mockito.when(personService.findById(1L)).thenReturn(person);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/person/1").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(json)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testGetRideByIdFail() throws Exception {
		String json = "";
		Person person = new Person();
		person.setId(1L);
		Mockito.when(personService.findById(2L)).thenReturn(person);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/person/1").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(json)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertNotEquals(200, status);
	}
  
}
