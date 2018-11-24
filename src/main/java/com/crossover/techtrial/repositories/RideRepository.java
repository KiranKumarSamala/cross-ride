/**
 * 
 */
package com.crossover.techtrial.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.Ride;

/**
 * @author crossover
 *
 */
@RestResource(exported = false)
public interface RideRepository extends CrudRepository<Ride, Long> {
	
	@Query(" select sum(TIME_TO_SEC(timediff(r.endTime,r.startTime))),"
			+ " max(TIME_TO_SEC(timediff(r.endTime,r.startTime))) , "
			+ "avg(r.distance), r.driver.name, r.driver.email " + 
			" from Ride r "
			+ " where r.startTime >= ?1 and r.endTime <= ?2 "
			+ " group by r.driver.id ")
			
	public List<Object> findTopnDrivers(Timestamp startTime,Timestamp endTime,Pageable p);
}
