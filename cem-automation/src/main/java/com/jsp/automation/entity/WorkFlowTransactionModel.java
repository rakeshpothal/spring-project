package com.jsp.automation.entity;

import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "workflow_transaction_info")
@Data
public class WorkFlowTransactionModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alt_key", columnDefinition = "BIGINT")
	private BigInteger altKey;
	
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name = "transaction_unique_value")
	private String transactionUniqueValue;
	
	@Column(name = "wf_id")
	private String wfId;
	
	@Column(name = "wf_code")
	private String wfCode;
	
	@Column(name = "Ststus_flag")
	private String ststusFlag;
	
	@Column(name = "transaction_start_date")
	private Date transactionStartDate;
	
	@Column(name = "transaction_end_date")
	private Date transactionEndDate;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private Date modifieddate;
}
