package com.jsp.automation.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.automation.entity.NodeDetailsModel;

/**
 * interface for database operation work for workflow node
 */
@Repository
public interface WorkFlowNodeRepository extends JpaRepository<NodeDetailsModel, BigInteger> {
	
	public List<NodeDetailsModel> findByWfCode(String wfCode);
	
}
