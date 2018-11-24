/**
 * 
 */
package com.crossover.techtrial.service;

import java.awt.print.Pageable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.RideRepository;

/**
 * @author crossover
 *
 */
@Service
public class RideServiceImpl implements RideService{

  @Autowired
  RideRepository rideRepository;
  
  public Ride save(Ride ride) {
    return rideRepository.save(ride);
  }
  
  public Ride findById(Long rideId) {
    Optional<Ride> optionalRide = rideRepository.findById(rideId);
    if (optionalRide.isPresent()) {
      return optionalRide.get();
    }else return null;
  }

  public List<TopDriverDTO> top5Drivers(Long count, LocalDateTime startTime,LocalDateTime endTime){
	  List<TopDriverDTO> topDrivers = new ArrayList<>();
	  
	  
	  Timestamp sqlStartTime = Timestamp.valueOf(startTime);
	  Timestamp sqlEndTime = Timestamp.valueOf(endTime);
			  
	  System.out.println(" service executed " + count);
	  List<Object> ridesList = rideRepository.findTopnDrivers(sqlStartTime, sqlEndTime,new PageRequest(0,count.intValue()));
	  ridesList.forEach(o -> {
		  TopDriverDTO driver = new TopDriverDTO();
		  Object[] obj = (Object[]) o;
		  
		  driver.setTotalRideDurationInSeconds((Long)obj[0]);
		  driver.setMaxRideDurationInSecods(((Integer)obj[1]).longValue());
		  driver.setAverageDistance((Double)obj[2]);
		  driver.setName((String)obj[3]);
		  driver.setEmail((String)obj[4]);

		  topDrivers.add(driver);
		  
	  });
	  return topDrivers;
  }
}
