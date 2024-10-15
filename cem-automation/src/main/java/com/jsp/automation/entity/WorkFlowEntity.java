package com.jsp.automation.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;



import com.jsp.automation.dto.NodeConfig;
import com.jsp.automation.util.NodeConfigBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "cem_workflow_info")
@Data
public class WorkFlowEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alt_key",columnDefinition = "BIGINT")
	private BigInteger altKey;
	
	@Column(name = "wf_id")
	private String wfId;
	
	@Column(name = "wf_code")
	private String wfCode;
	
	@Column(name = "wf_name")
	private String wfName;
	
	@Column(name = "version")
	private int version = 0;
	
	@Column(name = "status_flag")
	private String statusFlag = "DRAFT";
	
	@Column(name = "entity_code")
	private String entityCode;
	
	@Column(name = "unique_field")
	private String uniqueField;
	
	@Column(name = "source_data", columnDefinition = "LONGTEXT")
	private String sourceData;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Transient
	List<NodeDetailsModel> nodeDetails = new ArrayList<>();
	
	public List<NodeConfig> getStartNodeConfig() {
		try {
			List<NodeDetailsModel> nodeConfig = nodeDetails.stream().filter(each->each.getNodeType().toLowerCase().equals("start")).collect(Collectors.toList());
			return new NodeConfigBuilder().getNodeConfig(nodeConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<NodeConfig> getAllNodeConfig() {
		try {
			List<NodeConfig> nodeConfig = new NodeConfigBuilder().getNodeConfig(nodeDetails);
			return nodeConfig;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
