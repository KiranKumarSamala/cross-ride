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
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.RideRepository;
import com.crossover.techtrial.service.RideService;
import com.crossover.techtrial.service.RideServiceImpl;

@RunWith(SpringRunner.class)
public class RideServiceImplTest {

	 @TestConfiguration
	    static class EmployeeServiceImplTestContextConfiguration {
	  
	        @Bean
	        public RideService employeeService() {
	            return new RideServiceImpl();
	        }
	    }
	 
	 @Autowired
	 private RideService rideService;
	  
	 @MockBean
	 RideRepository rideRepository;
	 
	@Test
	public void testDriverCount5() {
		LocalDateTime startDt = LocalDateTime.parse("2018-11-02T09:00:00");
		LocalDateTime endDt = LocalDateTime.parse("2018-11-02T10:45:00");
		
		Timestamp sqlStartTime = Timestamp.valueOf(startDt);
		Timestamp sqlEndTime = Timestamp.valueOf(endDt);
		  
		List<Object> mock = new ArrayList<>();
		Object recArr1[] = {500L,500,50D,"A1","A1@A.COM"};
		Object recArr2[] = {500L,500,50D,"B1","B1@A.COM"};
		Object recArr3[] = {500L,500,50D,"C1","C1@A.COM"};
		Object recArr4[] = {500L,500,50D,"D1","D1@A.COM"};
		Object recArr5[] = {500L,500,50D,"E1","E1@A.COM"};
	
		mock.add((Object)recArr1);
		mock.add((Object)recArr2);
		mock.add((Object)recArr3);
		mock.add((Object)recArr4);
		mock.add((Object)recArr5);
		
		 
		Mockito.when(rideRepository.findTopnDrivers(sqlStartTime,sqlEndTime,new PageRequest(0,5))).thenReturn(mock);
		
		List<TopDriverDTO> topDrivers = rideService.top5Drivers(5L, startDt, endDt);
		
		assertEquals(5, topDrivers.size());
	}

	@Test
	public void testfindById() {
		long id = 1L;
		Ride ride = new Ride();
		ride.setId(id);
		Mockito.when(rideRepository.findById(id)).thenReturn(Optional.of(ride));
		
		Ride found = rideService.findById(id);
		assertEquals(id, found.getId().longValue());
	}
	
}
