package com.jsp.grantsource.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "grant_assignment_info")
public class GrantAssignmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alt_key")
	private long altKey;
	
	@Column(name = "plan_id")
	private long planId;
	
	@Column(name = "employee_number")
	private String employeeNumber;
	
	@Column(name = "bond")
	private String bond;
	
	@Column(name = "number_of_grante")
	private long numberOfGrante;
	
	@Column(name = "grant_price")
	private double grantPrice;
	
	@Column(name = "ststus")
	private String status;
	
	@Column(name = "lock_in_status")
	private String lockInStatus;
	
	@Column(name = "frequency")
	private long frequency;
	
	@Column(name = "grant_accepted")
	private String grantAccepted;
	
	@Column(name = "accepted_date")
	private Date acceptedDate;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "allocation_status")
	private String allocationStatus;
	
}
