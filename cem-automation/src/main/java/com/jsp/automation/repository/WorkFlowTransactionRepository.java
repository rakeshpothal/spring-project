package com.jsp.automation.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.automation.entity.WorkFlowTransactionModel;

public interface WorkFlowTransactionRepository extends JpaRepository<WorkFlowTransactionModel, BigInteger> {

}
