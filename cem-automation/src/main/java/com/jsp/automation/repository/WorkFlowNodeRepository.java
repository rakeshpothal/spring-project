package com.jsp.automation.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.automation.entity.NodeDetails;

/**
 * interface for database operation work for workflow node
 */
public interface WorkFlowNodeRepository extends JpaRepository<NodeDetails, BigInteger> {
	
	public void findByWfCode(String wfCode);
	
}
