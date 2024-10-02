package com.jsp.automation.util;

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import com.jsp.automation.dto.WorkFlowNodeDto;

public class XmlParser {
	public static void saxParserData(String xmlData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser newSAXParser = factory.newSAXParser();
			XmlParseHandler xmlParseHandler = new XmlParseHandler();
			InputSource inputSource = new InputSource(new StringReader(xmlData));
			newSAXParser.parse(inputSource, xmlParseHandler);
			
			List<WorkFlowNodeDto> workFlowNodeDtos = xmlParseHandler.getWorkFlowNodeDtos();
			for (WorkFlowNodeDto workFlowNodeDto : workFlowNodeDtos) {
				System.out.println(workFlowNodeDto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
