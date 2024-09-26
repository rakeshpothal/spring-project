package com.jsp.automation.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.automation.entity.WorkFlowEntity;


/**
 * it is an interface which extends {@link JpaRepository}
 * for database related operations
 */
@Repository
public interface WorkFlowRepository extends JpaRepository<WorkFlowEntity, BigInteger> {
	
	public WorkFlowEntity findByWfIdAndStstusFlag(String wfId, String status);

	public WorkFlowEntity findByWfCode(String wfCode);

	public List<WorkFlowEntity> findByWfId(String wfid);
}
