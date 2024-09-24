package com.jsp.grantsource.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.grantsource.DTO.GrantAssignmentDto;
import com.jsp.grantsource.Entity.GrantAssignmentEntity;
import com.jsp.grantsource.repository.GrantAssignmemntRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class GrantServiveImpl implements GrantService {

	@Autowired
	private GrantAssignmemntRepository grantAssignmemntRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void processGrante(List<GrantAssignmentDto> grantAssignmentDtos) {

		List<GrantAssignmentEntity> grantAssignmentEntityList = new ArrayList<GrantAssignmentEntity>();
		for (GrantAssignmentDto grantAssignmentDto : grantAssignmentDtos) {
			GrantAssignmentEntity grantAssignmentEntity = new GrantAssignmentEntity();

			grantAssignmentEntity.setPlanId(grantAssignmentDto.getPlanId());
			grantAssignmentEntity.setEmployeeNumber(grantAssignmentDto.getEmployeeNumber());
			grantAssignmentEntity.setBond(grantAssignmentDto.getBond());
			grantAssignmentEntity.setNumberOfGrante(grantAssignmentDto.getNumberOfGrante());
			grantAssignmentEntity.setGrantPrice(grantAssignmentDto.getGrantPrice());
			grantAssignmentEntity.setStatus("PENDING");
			grantAssignmentEntity.setLockInStatus("OPEN");
			grantAssignmentEntity.setFrequency(grantAssignmentDto.getFrequency());
			grantAssignmentEntity.setGrantAccepted("NO");
			grantAssignmentEntity.setCreatedDate(new Date());
			grantAssignmentEntity.setAllocationStatus("PENDING");

			grantAssignmentEntityList.add(grantAssignmentEntity);

		}

		grantAssignmemntRepository.saveAll(grantAssignmentEntityList);

	}

	@Override
	public List<GrantAssignmentEntity> listAllGrant() {
		return grantAssignmemntRepository.findAll();
	}

	@Override
	public GrantAssignmentEntity fetchGrantbyId(long id) {
//		return grantAssignmemntRepository.findById(id).orElse(null);
		Optional<GrantAssignmentEntity> getGrantbyID = grantAssignmemntRepository.findById(id);
		return getGrantbyID.get();
	}

	@Override
	public void approveGrantById(List<Long> grantIdList) {
		List<GrantAssignmentEntity> grantList = grantAssignmemntRepository.findAllById(grantIdList);

		for (GrantAssignmentEntity grantAssignmentEntity : grantList) {
			if (grantAssignmentEntity.getStatus().equals("PENDING")) {
				grantAssignmentEntity.setStatus("APPROVED");
				grantAssignmentEntity.setAcceptedDate(new Date());
			}
		}
		grantAssignmemntRepository.saveAll(grantList);

	}

	@Override
	public List<GrantAssignmentEntity> getGrantsForAllocationBYId(long planId) {
		return grantAssignmemntRepository.getGrantById(planId);
	}

	@Override
	public List<Map<String, Object>> getGrantListForAllocatioByPlanId(long planId) {

//		List<Map<String, Object>> listOfMap = new ArrayList<>();

		List<GrantAssignmentEntity> entities = grantAssignmemntRepository
				.findByStatusAndAllocationStatusAndPlanId("APPROVED", "PENDING", planId);

		List<Map<String, Object>> listOfMap = entities.stream().map(grantAssignmentEntity -> {
			Map<String, Object> entityMap = new HashMap<>();
			entityMap.put("grantId", grantAssignmentEntity.getPlanId());
			entityMap.put("frequency", grantAssignmentEntity.getFrequency());
			entityMap.put("grantNumber", grantAssignmentEntity.getNumberOfGrante());
			entityMap.put("bond", grantAssignmentEntity.getBond());
			Date grantDate = null;
			if (grantAssignmentEntity.getModifiedDate() != null) {
				grantDate = grantAssignmentEntity.getModifiedDate();
			} else {
				grantDate = grantAssignmentEntity.getCreatedDate();
			}
			entityMap.put("grantDate", grantDate);
			return entityMap;
		}).collect(Collectors.toList());

//		for (GrantAssignmentEntity grantAssignmentEntity : entities) {
//			Map<String, Object> entityMap = new HashMap<>();
//			entityMap.put("grantId", grantAssignmentEntity.getPlanId());
//			entityMap.put("frequency", grantAssignmentEntity.getFrequency());
//			entityMap.put("grantNumber", grantAssignmentEntity.getNumberOfGrante());
//			entityMap.put("bond", grantAssignmentEntity.getBond());
//			Date grantDate = null;
//			if (grantAssignmentEntity.getModifiedDate() != null) {
//				grantDate = grantAssignmentEntity.getModifiedDate();
//			} else {
//				grantDate = grantAssignmentEntity.getCreatedDate();
//			}
//			entityMap.put("grantDate", grantDate);
//			listOfMap.add(entityMap);
//		}
		return listOfMap;
	}

}
