package com.jsp.automation.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.automation.entity.WorkFlowEntity;

/**
 * it is an interface which extends {@link JpaRepository} for database related
 * operations
 * database operations for workflow entity
 */
@Repository
public interface WorkFlowRepository extends JpaRepository<WorkFlowEntity, BigInteger> {

	public WorkFlowEntity findByWfIdAndStatusFlag(String wfId, String status);

	public WorkFlowEntity findByWfCode(String wfCode);

	public List<WorkFlowEntity> findByWfId(String wfid);
	
	@Transactional
	@Modifying
	@Query(value = "update WorkFlowEntity set statusFlag=:statusFlag where wfCode!=:wfCode and wfId=:wfId and statusFlag='ACTIVE'")
	public void updateByWfIdAndWfCodeSetStatusFlag(String wfId, String wfCode, String statusFlag);
	
	@Query(value = "select max(version) from WorkFlowEntity where wfId=:wfId group by wfId")
	public int findByWfIdMaxVersionGroupByWfId(String wfId);
	
	
}
