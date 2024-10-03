package com.jsp.automation.util;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Component
@Converter
public class ConvertListOfMapToString implements AttributeConverter<List<Map<String, String>>, String> {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(List<Map<String, String>> attribute) {
		if (attribute == null || attribute.isEmpty()) {
			return null;
		}
		try {
			return objectMapper.writeValueAsString(attribute);
		} catch (Exception e) {
			throw new IllegalArgumentException("Error in converting list of map to string");
		}
	}

	@Override
	public List<Map<String, String>> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.isEmpty()) {
			return null;
		}
		try {
			return objectMapper.readValue(dbData, new TypeReference<List<Map<String, String>>>() {
			});
		} catch (Exception e) {
			throw new IllegalArgumentException("error converting string to list of map");
		}
	}

}
