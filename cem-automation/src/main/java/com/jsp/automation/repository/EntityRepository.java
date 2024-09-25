package com.jsp.automation.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.automation.entity.EntityModel;


@Repository
public interface EntityRepository extends JpaRepository<EntityModel, BigInteger> {

}
