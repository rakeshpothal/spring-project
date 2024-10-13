package com.jsp.automation.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.automation.entity.WorkflowTransactionLogModel;


@Repository
public interface WorkflowTransactionLogModelRepository extends JpaRepository<WorkflowTransactionLogModel, BigInteger> {

}
