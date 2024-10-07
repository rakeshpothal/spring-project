package com.jsp.automation.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.automation.dto.NodeConfig;
import com.jsp.automation.entity.NodeDetailsModel;

@Component
public class NodeConfigBuilder {
	@Autowired
	private ConvertMapToString convertMapToString;

	@Autowired
	private Converter converter;

	public List<NodeConfig> getNodeConfig(List<NodeDetailsModel> nodeDetailsList) {
		Map<String, NodeConfig> nodeConfigs = new HashMap<>();
		for (NodeDetailsModel nodeDetails : nodeDetailsList) {
			if (!nodeConfigs.containsKey(nodeDetails.getNodeId()))
				buildNodeConfig(nodeDetails, nodeDetailsList, nodeConfigs);
			else {
				NodeConfig nodeConfig = nodeConfigs.get(nodeDetails.getNodeId());
				if (outgoingNodesExist(nodeDetails))
					addOutGoingNodes(nodeConfig, nodeDetails, nodeDetailsList, nodeConfigs);
				if (incomingNodesExist(nodeDetails))
					addIncomingNodes(nodeConfig, nodeDetails, nodeDetailsList, nodeConfigs);
			}
		}
		return new ArrayList<>(nodeConfigs.values());
	}

	public void buildNodeConfig(NodeDetailsModel nodeDetails, List<NodeDetailsModel> nodeDetailsList,
			Map<String, NodeConfig> nodeConfigs) {
		NodeConfig nodeConfig = new NodeConfig();
		nodeConfig.setNodeId(nodeDetails.getNodeId());
		nodeConfig.setNodeType(nodeDetails.getNodeType());
		nodeConfig.setNodeProperties(convertMapToString.convertStringToMap(nodeDetails.getNodeProperties()));
		if ("start".equals(nodeDetails.getNodeType()))
			nodeConfig.setStartnode(false);
		if ("end".equals(nodeDetails.getNodeType()))
			nodeConfig.setEndNode(false);
		nodeConfigs.put(nodeDetails.getNodeId(), nodeConfig);
		if (outgoingNodesExist(nodeDetails))
			addOutGoingNodes(nodeConfig, nodeDetails, nodeDetailsList, nodeConfigs);
		if (incomingNodesExist(nodeDetails))
			addIncomingNodes(nodeConfig, nodeDetails, nodeDetailsList, nodeConfigs);
		nodeConfigs.put(nodeDetails.getNodeId(), nodeConfig);
	}

	private void addIncomingNodes(NodeConfig nodeConfig, NodeDetailsModel nodeDetails,
			List<NodeDetailsModel> nodeDetailsList, Map<String, NodeConfig> nodeConfigs) {

		List<String> incomingNodes = converter.convertToEntityAttribute(nodeDetails.getIncomingNodes());

		if (incomingNodes == null)
			return;

		for (String incomingNode : incomingNodes) {
			if (incomingNodeExistsWithNodeConfig(incomingNode, nodeConfig))
				continue;
			if (!nodeConfigs.containsKey(incomingNode)) {
				NodeDetailsModel incomingNodeDetails = getNodeDetails(incomingNode, nodeDetailsList);
				if (incomingNodeDetails == null)
					continue;
				buildNodeConfig(incomingNodeDetails, nodeDetailsList, nodeConfigs);
			}

			List<NodeConfig> nodeConfigList = new ArrayList<>();
			nodeConfigList.add(nodeConfigs.get(incomingNode));
			nodeConfig.setIncomingNode(nodeConfigList);
		}
	}

	private boolean incomingNodeExistsWithNodeConfig(String incomingNode, NodeConfig nodeConfig) {
		List<NodeConfig> incomingNode2 = nodeConfig.getIncomingNode();
		if (incomingNode2 != null) {
			for (NodeConfig incomingNodeConfig : incomingNode2) {
				if (incomingNodeConfig.getNodeId().equals(incomingNode))
					return true;
			}
		}
		return false;
	}

	private NodeDetailsModel getNodeDetails(String incomingNode, List<NodeDetailsModel> nodeDetailsList) {
		for (NodeDetailsModel nodeDetails : nodeDetailsList) {
			if (nodeDetails.getNodeId().equals(incomingNode))
				return nodeDetails;
		}
		return null;
	}

	private boolean incomingNodesExist(NodeDetailsModel nodeDetails) {
		return nodeDetails.getIncomingNodes() != null;
	}

	private boolean outgoingNodesExist(NodeDetailsModel nodeDetails) {
		return nodeDetails.getOutgoingNodes() != null;
	}

	private void addOutGoingNodes(NodeConfig nodeConfig, NodeDetailsModel nodeDetails,
			List<NodeDetailsModel> nodeDetailsList, Map<String, NodeConfig> nodeConfigs) {
		List<String> outgoingNodes = converter.convertToEntityAttribute(nodeDetails.getOutgoingNodes());
		if (outgoingNodes == null)
			return;
		for (String outgoingNode : outgoingNodes) {
			if (outgoingNodeExistsWithNodeConfig(outgoingNode, nodeConfig))
				continue;
			if (!nodeConfigs.containsKey(outgoingNode)) {
				NodeDetailsModel outgoingNodeDetails = getNodeDetails(outgoingNode, nodeDetailsList);
				if (outgoingNodeDetails == null)
					continue;
				buildNodeConfig(outgoingNodeDetails, nodeDetailsList, nodeConfigs);
			}
			List<NodeConfig> outgoingNodeList = new ArrayList<>();
			outgoingNodeList.add(nodeConfigs.get(outgoingNode));
			nodeConfig.setOutgoingNode(outgoingNodeList);

		}
	}

	private boolean outgoingNodeExistsWithNodeConfig(String outgoingNode, NodeConfig nodeConfig) {
		List<NodeConfig> outgoingNode2 = nodeConfig.getOutgoingNode();
		if (outgoingNode2 != null)
			for (NodeConfig outgoingNodeConfig : outgoingNode2) {
				if (outgoingNodeConfig.getNodeId().equals(outgoingNode))
					return true;
			}
		return false;
	}
}
