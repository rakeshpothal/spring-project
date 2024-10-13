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

/**
 * its a entity class which will be stored in the database
 * 
 */
@Data
@Entity
@Table(name = "entity_model_info")
public class EntityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alt_key", columnDefinition = "BIGINT")
	private BigInteger altKey;

	@Column(name = "entity_code", unique = true, nullable = false)
	private String entityCode;

	@Column(name = "entity_name")
	private String entityName;

	@Column(name = "entity_description")
	private String EntityDescription;

	@Column(name = "unique_fields", columnDefinition = "MEDIUMTEXT")
	private String uniqueFields;

	@Column(name = "template_fields", columnDefinition = "MEDIUMTEXT")
	private String templateFields;

	@Column(name = "status_field_value", columnDefinition = "LONGTEXT")
	private String statusFieldValues;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	private Date modifiedDate;
}
