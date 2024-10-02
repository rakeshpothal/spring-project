package com.jsp.automation.entity;

import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "cem_wf_node_info")
@Data
public class NodeDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alt_key", columnDefinition = "BIGINT")
	private BigInteger altKey;

	@Column(name = "node_id")
	private String nodeId;

	@Column(name = "wf_code")
	private String wfCode;

	@Column(name = "incoming_node")
	private String incomingNodes;

	@Column(name = "outgoing_nodes")
	private String outgoingNodes;

	@Column(name = "nodeproperties", columnDefinition = "LONGTEXT")
	private String nodeProperties;

	@Column(name = "node_type")
	private String nodeType;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	private Date modifiedDate;

	

}
