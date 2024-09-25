package com.jsp.automation.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.automation.entity.EntityModel;

/**
 * it is a interface which extends {@link JpaRepository} interface it is
 * responsible for database operations for {@link EntityModel} class with
 * primary key {@link BigInteger}
 * 
 */
@Repository
public interface EntityRepository extends JpaRepository<EntityModel, BigInteger> {
	public EntityModel findByEntityCode(String entityCode);
}
