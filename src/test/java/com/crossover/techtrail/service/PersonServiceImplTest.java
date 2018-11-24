package com.crossover.techtrail.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.PersonRepository;
import com.crossover.techtrial.repositories.RideRepository;
import com.crossover.techtrial.service.PersonService;
import com.crossover.techtrial.service.PersonServiceImpl;
import com.crossover.techtrial.service.RideService;
import com.crossover.techtrial.service.RideServiceImpl;

@RunWith(SpringRunner.class)
public class PersonServiceImplTest {

	 @TestConfiguration
	    static class PersonServiceImplTestContextConfiguration {
	  
	        @Bean
	        public PersonService personService() {
	            return new PersonServiceImpl();
	        }
	    }
	 
	 @Autowired
	 private PersonService personService;
	  
	 @MockBean
	 PersonRepository personRepository;
	 
	@Test
	public void testGetAll() {
		List<Person> personList = new ArrayList();
		Person person = new Person();
		person.setId(1L);
		personList.add(person);
		
		Mockito.when(personRepository.findAll()).thenReturn(personList);
		
		List<Person> persons = personService.getAll();
		
		assertEquals(1, persons.size());
	}

	@Test
	public void testfindById() {
		long id = 1L;
		Person person = new Person();
		person.setId(id);
		Mockito.when(personRepository.findById(id)).thenReturn(Optional.of(person));
		
		Person found = personService.findById(id);
		assertEquals(id, found.getId().longValue());
	}
	
}
