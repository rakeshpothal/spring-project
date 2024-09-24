package com.jsp.usm.entity;

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
@Table(name = "user_register_info")
public class RegisterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	GeneratedValue(generat)
	@Column(name = "alt_key")
	private long altKey;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "contact")
	private String contact;

	@Column(name = "city")
	private String city;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;

	

}
