package com.jsp.usm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jsp.usm.controller.RegisterController;
import com.jsp.usm.dto.AppResponseDto;
import com.jsp.usm.dto.RegisterDto;
import com.jsp.usm.dto.SmsDto;
import com.jsp.usm.entity.RegisterEntity;
import com.jsp.usm.repository.MySqlRepository;
import com.jsp.usm.repository.RegisterRepository;

@Service
public class RegisterServiceImpl implements RegisterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private MySqlRepository mySqlRepository;

	@Autowired
	private RegisterRepository registerRepository;

	@Autowired
	@Qualifier("resttemplate")
	private RestTemplate restTemplate;

	@Override
	public AppResponseDto sendSms(SmsDto smsDto) {

		try {
			String url = "http://localhost:8084/notification-service/sendsms";
			LOGGER.info("invoking sendSms of notification service url:{}", url);
			HttpEntity<SmsDto> httpEntity = new HttpEntity<>(smsDto);
			ResponseEntity<AppResponseDto> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
					AppResponseDto.class);
			LOGGER.debug("send sms response response:{}", response.getBody());
			return response.getBody();
		} catch (Exception e) {
			LOGGER.error("exception occoured while invoking the notification service SmsDto:{},e:{}", smsDto, e);
		}
		return null;
	}

	public RegisterServiceImpl() {
		System.out.println(this.getClass().getSimpleName());
	}

	@Override
	public List<RegisterEntity> getUsers() {
//		Sort sort = Sort.by(Sort.Direction.fromString("asc"),"city");
//		return registerRepository.findAll(sort);
		return registerRepository.findAll();
	}

	@Override
	public Page<RegisterEntity> getUsers(int pageNumber, int pageSize, String sortingField, String SortingOrder) {
		Sort sort = Sort.by(Sort.Direction.fromString(SortingOrder), sortingField);
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
		Page<RegisterEntity> all = registerRepository.findAll(pageRequest);
		return all;
	}

	@Override
	public RegisterEntity getUserByIdValue(long id) {
		System.out.println("hello impl is running");
//		return registerRepository.getOne(id);
//		Optional<RegisterEntity> byId = registerRepository.findById(id);
//		return (RegisterEntity) byId;
		return null;
	}

	@Override
	public int getdataCount() {
		return (int) registerRepository.count();
	}

	@Override
	public void saveUser(RegisterDto registerDto) {
		RegisterEntity registerEntity = new RegisterEntity();
		registerEntity.setCity(registerDto.getCity());
		registerEntity.setContact(registerDto.getContact());
		registerEntity.setCreatedDate(new Date());
		registerEntity.setEmail(registerDto.getEmail());
		registerEntity.setName(registerDto.getName());
		registerEntity.setPincode(registerDto.getPincode());

		registerRepository.save(registerEntity);

	}

	@Override
	public List<RegisterEntity> getUserByCity(String city) {
		return registerRepository.getUserByCity(city);
	}

	@Override
	public List<RegisterEntity> getuserByCityAndName(String city, String name) {
//		return registerRepository.getUserByCityAndName(city, name);
		return registerRepository.findByCityAndName(city, name);
	}

	@Override
	public List<RegisterEntity> getUserByCity(List<String> cityList) {
		return registerRepository.findByCityIn(cityList);
	}

	@Override
	public List<RegisterEntity> getAllUsers() {
		List<Map<String, Object>> users = mySqlRepository.getUsers();

		List<RegisterEntity> registerEntityList = users.stream().map(each -> {

			RegisterEntity registerEntity = new RegisterEntity();
			registerEntity.setAltKey(Long.parseLong(each.get("alt_key").toString()));
			registerEntity.setCity(each.get("city").toString());
			registerEntity.setContact(each.get("contact").toString());
			registerEntity.setEmail(each.get("email").toString());
			registerEntity.setName(each.get("name").toString());
			registerEntity.setPincode(each.get("pincode").toString());
			try {
				registerEntity
						.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse(each.get("created_date").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return registerEntity;

		}).collect(Collectors.toList());

		return registerEntityList;
	}

	@Override
	public List<RegisterEntity> getAllUsers(String email) {
		List<Map<String, Object>> users = mySqlRepository.getUserByEmail(email);

		List<RegisterEntity> registerEntityList = users.stream().map(each -> {

			RegisterEntity registerEntity = new RegisterEntity();
			registerEntity.setAltKey(Long.parseLong(each.get("alt_key").toString()));
			registerEntity.setCity(each.get("city").toString());
			registerEntity.setContact(each.get("contact").toString());
			registerEntity.setEmail(each.get("email").toString());
			registerEntity.setName(each.get("name").toString());
			registerEntity.setPincode(each.get("pincode").toString());
			try {
				registerEntity
						.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse(each.get("created_date").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return registerEntity;

		}).collect(Collectors.toList());

		return registerEntityList;
	}

	@Override
	public List<RegisterEntity> getAllUsers(String email, String name) {
		List<Map<String, Object>> users = mySqlRepository.getUserByEmailAndNme(email, name);

		List<RegisterEntity> registerEntityList = users.stream().map(each -> {

			RegisterEntity registerEntity = new RegisterEntity();
			registerEntity.setAltKey(Long.parseLong(each.get("alt_key").toString()));
			registerEntity.setCity(each.get("city").toString());
			registerEntity.setContact(each.get("contact").toString());
			registerEntity.setEmail(each.get("email").toString());
			registerEntity.setName(each.get("name").toString());
			registerEntity.setPincode(each.get("pincode").toString());
			try {
				registerEntity
						.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse(each.get("created_date").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return registerEntity;

		}).collect(Collectors.toList());

		return registerEntityList;
	}

	@Override
	public List<RegisterEntity> getAllUsers(List<String> dataList) {
		List<Map<String, Object>> users = mySqlRepository.getUserByEmailAndNameList(dataList);

		List<RegisterEntity> registerEntityList = users.stream().map(each -> {

			RegisterEntity registerEntity = new RegisterEntity();
			registerEntity.setAltKey(Long.parseLong(each.get("alt_key").toString()));
			registerEntity.setCity(each.get("city").toString());
			registerEntity.setContact(each.get("contact").toString());
			registerEntity.setEmail(each.get("email").toString());
			registerEntity.setName(each.get("name").toString());
			registerEntity.setPincode(each.get("pincode").toString());
			try {
				registerEntity
						.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse(each.get("created_date").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return registerEntity;

		}).collect(Collectors.toList());

		return registerEntityList;
	}
}
