package com.jsp.automation.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	private final ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		if (attribute != null) {
//			return String.join(",", attribute);
			try {
				return objectMapper.writeValueAsString(attribute);
			} catch (JsonProcessingException e) {
				throw new IllegalArgumentException("Error in converting list of String to string");
			}
		}
		return null;
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		if (dbData != null) {
//			return Arrays.asList(dbData.split(","));
			try {
				return objectMapper.readValue(dbData, new TypeReference<List<String>>() {
				});
			} catch (JsonProcessingException e) {
				throw new IllegalArgumentException("error converting string to list if String");
			}
		}
		return null;
	}

}
