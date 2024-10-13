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
@Data
@Table(name = "workflow_transaction_log_info")
public class WorkflowTransactionLogModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alt_key", columnDefinition = "BIGINT")
	private BigInteger altKey;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "transaction_unique_value")
	private String transactionUniqueValue;

	@Column(name = "workflow_id")
	private String wfid;

	@Column(name = "current_node_id")
	private String currentNodeId;

	@Column(name = "current_node_type")
	private String currentNodeType;

	@Column(name = "status_flag")
	private String statusFlag;

	@Column(name = "execution_signal")
	private String executionSignal;

	@Column(name = "current_thread")
	private String currentThread;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	private Date modefieddate;

	@Column(name = "previous_node_id")
	private String previousNodeId;

	@Column(name = "trigger_date")
	private Date triggerDate;

	@Column(name = "ytransaction_start_date")
	private Date transactionStartDate;

	@Column(name = "transaction_end_date")
	private Date transactionEndDtae;

}
