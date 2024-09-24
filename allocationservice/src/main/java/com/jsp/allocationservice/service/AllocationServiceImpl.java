package com.jsp.allocationservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jsp.allocationservice.entity.AllocationEntity;
import com.jsp.allocationservice.repository.AllocationRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AllocationServiceImpl implements AllocationService {

	@Autowired
	private AllocationRepository allocationRepository;

	@Autowired
	private RestTemplate restTemplate;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void processAndPrepareAllocation(List<Map<String, Object>> listData) {
//		for (Map<String, Object> map : listData) {
//			System.out.println(map.get("grantId"));
//		}
		
		
		List<AllocationEntity> allocationEntityList = new ArrayList<AllocationEntity>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		for (Map<String, Object> map : listData) {

			long frequency = ((Number) map.get("frequency")).longValue();
			long grantNumber = ((Number) map.get("grantNumber")).longValue();
			long allocationNumber = grantNumber / frequency;
			long grantId = ((Number) map.get("grantId")).longValue();
			String grantDateString = (String) map.get("grantDate");

			for (int i = 1; i <= frequency; i++) {

				LocalDateTime grantdate = LocalDateTime.parse(grantDateString, formatter).plusYears(i);
				String year = String.valueOf(grantdate.getYear());
				AllocationEntity allocationEntity = new AllocationEntity();
				allocationEntity.setGrantId(grantId);
				allocationEntity.setAllocationNumber(allocationNumber);
				allocationEntity.setAllocationYear(year);

				Date allocationDate = null;
				try {
					allocationDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(grantDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				allocationEntity.setAllocationDate(allocationDate);
				allocationEntity.setAllocationStatus("PENDING");
				allocationEntity.setCreatedDate(new Date());

				allocationEntityList.add(allocationEntity);
			}

		}
		allocationRepository.saveAll(allocationEntityList);

	}

	@Override
//	@Scheduled(fixedDelay = 1000)
//	@Scheduled(cron = )
	@Scheduled(fixedRate = 10000)
	public void getGrantsForAllocation() {
//		System.out.println("scheduled method is running");
		long planId = 12345698754l;
		List<Map<String, Object>> grantsForAllocationById = getGrantsForAllocationById(planId);
		processAndPrepareAllocation(grantsForAllocationById);

	}

	private List<Map<String, Object>> getGrantsForAllocationById(long planId) {
		String url = "http://localhost:8082/grantsource/getgrantlistbyplanid/" + planId;
		HttpEntity<Object> httpEntity = new HttpEntity<>(null);
		ResponseEntity<List<Map<String, Object>>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
				httpEntity, new ParameterizedTypeReference<List<Map<String, Object>>>() {
				});
		return responseEntity.getBody();

	}

}
