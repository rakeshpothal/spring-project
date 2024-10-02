package com.jsp.automation.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jsp.automation.dto.NodeDetailsDto;

public class XmlParseHandler extends DefaultHandler {
	private List<NodeDetailsDto> workFlowNodeDtos = new ArrayList<NodeDetailsDto>();
	private List<String> incomingNodes;
	private List<String> outgoingNodes;
	private Map<String, Object> nodeProperties;
	private String nodeType;

	private boolean isTaskOrEvent = false;
	private boolean isIncoming = false;
	private boolean isOutgoing = false;

	public List<NodeDetailsDto> getWorkFlowNodeDtos() {
		return workFlowNodeDtos;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) {

		switch (qName) {
		case "bpmn:startEvent":
		case "bpmn:endEvent":
		case "bpmn:task":
		case "bpmn:exclusiveGateway":
			nodeProperties = new HashMap<String, Object>();
			incomingNodes = new ArrayList<String>();
			outgoingNodes = new ArrayList<String>();

			isTaskOrEvent = true;
			nodeProperties.put("id", atts.getValue("id"));
			nodeProperties.put("name", atts.getValue("name"));
			nodeType = atts.getValue("name");
			break;
		case "bpmn:incoming":
			isIncoming = true;
			break;
		case "bpmn:outgoing":
			isOutgoing = true;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "bpmn:startEvent":
		case "bpmn:endEvent":
		case "bpmn:task":
		case "bpmn:exclusiveGateway":
			// Finalize the WorkFlowNodeDto and add to the list
			NodeDetailsDto workFlowNodeDto = new NodeDetailsDto();
			workFlowNodeDto.setIncomingNodes(incomingNodes);
			workFlowNodeDto.setOutgoingNodes(outgoingNodes);
			workFlowNodeDto.setNodeProperties(nodeProperties);
			workFlowNodeDto.setNodeType(nodeType);

			workFlowNodeDtos.add(workFlowNodeDto);

			isTaskOrEvent = false;
			incomingNodes = null;
			outgoingNodes = null;
			nodeProperties = null;
			nodeType = null;
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length).trim();
		if (isIncoming) {
			incomingNodes.add(content); // Add the incoming flow
			isIncoming = false; // Reset flag after processing
		}

		if (isOutgoing) {
			outgoingNodes.add(content); // Add the outgoing flow
			isOutgoing = false; // Reset flag after processing
		}
	}

}
