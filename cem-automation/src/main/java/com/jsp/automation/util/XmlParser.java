package com.jsp.automation.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jsp.automation.dto.NodeDetailsDto;

public class XmlParser {
	public static List<NodeDetailsDto> saxParserData(String xmlData) throws SAXException, IOException, ParserConfigurationException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser newSAXParser = factory.newSAXParser();
		XmlParseHandler xmlParseHandler = new XmlParseHandler();
		InputSource inputSource = new InputSource(new StringReader(xmlData));
		newSAXParser.parse(inputSource, xmlParseHandler);

		List<NodeDetailsDto> workFlowNodeDtos = xmlParseHandler.getWorkFlowNodeDtos();
//			for (NodeDetailsDto workFlowNodeDto : workFlowNodeDtos) {
//				System.out.println(workFlowNodeDto);
//			}
		return workFlowNodeDtos;

	}

}
