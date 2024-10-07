package com.jsp.automation.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.automation.entity.NodeDetailsModel;

/**
 * interface for database operation work for workflow node
 */
public interface WorkFlowNodeRepository extends JpaRepository<NodeDetailsModel, BigInteger> {
	
	public List<NodeDetailsModel> findByWfCode(String wfCode);
	
}
