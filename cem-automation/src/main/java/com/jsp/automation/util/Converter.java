package com.jsp.automation.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;

/**
 * converter class is used to convert list<string> to string and reverse its
 * impliments {@link AttributeConverter} interface with two overridden method
 * 
 * @convertToDatabaseColumn(String attribute)
 * @convertToEntityAttribute(String dbData)
 * 
 */
@Component
public class Converter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		if (attribute != null) {
			return String.join(",", attribute);
		}
		return null;
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		if (dbData != null) {
			return Arrays.asList(dbData.split(","));
		}
		return null;
	}

}
