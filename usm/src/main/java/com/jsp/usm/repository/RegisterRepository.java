package com.jsp.usm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.usm.entity.RegisterEntity;

public interface RegisterRepository extends JpaRepository<RegisterEntity, Long> {
	
	@Query(value = "from RegisterEntity where city=:city")
	public List<RegisterEntity> getUserByCity(String city);
	
	@Query(value = "from RegisterEntity where city=:city and name=:name")
	public List<RegisterEntity> getUserByCityAndName(String city, String name);
	
	public List<RegisterEntity> findByCityAndName(String city, String name);
	
	public List<RegisterEntity> findByCityIn(List<String> cityList);
	
//	public void findByCityOrderByCityDescLimit1();//to get sorted data with city desc
	
}
