package com.crossover.techtrial.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.service.RideService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RideControllerTest {

	@Autowired
	MockMvc mockMvc;
	  
	@MockBean
	RideService rideService;

	@Test
	public void testFetchTop5Drivers() throws Exception {
		 
		LocalDateTime startDt = LocalDateTime.parse("2018-11-02T09:00:00");
		LocalDateTime endDt = LocalDateTime.parse("2018-11-02T10:45:00");
		 
		String json = "";
		List<TopDriverDTO> topDrivers = new ArrayList<>();
		 
		Mockito.when(rideService.top5Drivers(5L,startDt,endDt)).thenReturn(topDrivers);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/top-rides?startTime=2018-11-02T09:00:00&endTime=2018-11-02T10:45:00").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(json)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);

	}

	@Test
	public void testGetRideById() throws Exception {
		String json = "";
		Ride ride = new Ride();
		ride.setId(1L);
		Mockito.when(rideService.findById(1L)).thenReturn(ride);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/ride/1").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(json)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testGetRideByIdFail() throws Exception {
		String json = "";
		Ride ride = new Ride();
		ride.setId(1L);
		Mockito.when(rideService.findById(2L)).thenReturn(ride);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/ride/1").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(json)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertNotEquals(200, status);
	}
}
