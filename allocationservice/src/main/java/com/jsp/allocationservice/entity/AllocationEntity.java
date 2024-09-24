package com.jsp.allocationservice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "allocation_details")
@Data
public class AllocationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alt_key")
	private long altKey;
	
	@Column(name = "grant_id")
	private long grantId;
	
	@Column(name = "allocation_number")
	private long allocationNumber;
	
	@Column(name = "allocation_year")
	private String allocationYear;
	
	@Column(name = "allocation_date")
	private Date allocationDate;
	
	@Column(name = "allocation_status")
	private String allocationStatus;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_date")
	private Date modifieddate;
}
