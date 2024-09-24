package com.jsp.grantsource.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsp.grantsource.Entity.GrantAssignmentEntity;

@Repository
public interface GrantAssignmemntRepository extends JpaRepository<GrantAssignmentEntity, Long> {
	
	@Query(value = "from GrantAssignmentEntity where status='APPROVED'  AND allocationStatus='PENDING' AND planId=:planId")
	public List<GrantAssignmentEntity> getGrantById(Long planId);
	
	public List<GrantAssignmentEntity> findByStatusAndAllocationStatusAndPlanId(String status,String allocationStatus, long planId);
}
