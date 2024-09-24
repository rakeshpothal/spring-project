package com.jsp.usm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.usm.constants.MappingConstant;
import com.jsp.usm.dto.AppResponseDto;
import com.jsp.usm.dto.RegisterDto;
import com.jsp.usm.dto.SmsDto;
import com.jsp.usm.entity.RegisterEntity;
import com.jsp.usm.service.RegisterService;

@RestController
public class RegisterController {
	@Autowired
	private RegisterService registerService;

	@RequestMapping(value = MappingConstant.GET_USERS)
	public @ResponseBody List<RegisterEntity> getUsers() {
		return registerService.getUsers();
	}

	@GetMapping(value = MappingConstant.GET_SORTED_USERS)
	public @ResponseBody Page<RegisterEntity> getUsers(@RequestParam int pageNumber, @RequestParam int pageSize,
			@RequestParam String sortingField, @RequestParam String sortingOrder) {
		return registerService.getUsers(pageNumber, pageSize, sortingField, sortingOrder);
	}

	@RequestMapping(value = MappingConstant.GET_USERSBY_ID)
	public @ResponseBody RegisterEntity getUsersById() {
		long id = 1;
		return registerService.getUserByIdValue(id);
	}

	@RequestMapping(value = MappingConstant.GET_DATA_COUNT)
	public int getDataCount() {
		return registerService.getdataCount();
	}

	@RequestMapping(value = MappingConstant.SAVE_USER)
	public void saveUserData(@RequestBody RegisterDto registerDto) {
		registerService.saveUser(registerDto);
	}

	@GetMapping(value = MappingConstant.GET_USER_BY_CITY)
	public @ResponseBody List<RegisterEntity> getUserByCity(@PathVariable("city") String city) {
		return registerService.getUserByCity(city);
	}

	@GetMapping(value = MappingConstant.GET_USER_BY_CITY_AND_NAME)
	public @ResponseBody List<RegisterEntity> getUserbyCityAndName(@PathVariable("city") String city,
			@PathVariable("name") String namString) {
		return registerService.getuserByCityAndName(city, namString);
	}

	@GetMapping(value = MappingConstant.GET_USER_BY_CITY_AND_NAME_DUPLICATE)
	public @ResponseBody List<RegisterEntity> getUserByCityAndNameDuplicate(@RequestHeader("city") String city,
			@RequestHeader("name") String name) {
		return registerService.getuserByCityAndName(city, name);
	}

	@PostMapping(value = MappingConstant.GET_USER_BY_CITY_LIST)
	public @ResponseBody List<RegisterEntity> getUserByCityList(@RequestBody List<String> cityList) {
		return registerService.getUserByCity(cityList);
	}

	@PostMapping(value = MappingConstant.SEND_SMS)
	public AppResponseDto sendSms(@RequestBody SmsDto smsDto) {
		AppResponseDto sendSms = registerService.sendSms(smsDto);
		return sendSms;
	}

	@GetMapping(value = MappingConstant.GET_ALL_USERS)
	public @ResponseBody List<RegisterEntity> getallUsers() {
		return registerService.getAllUsers();
	}

	@GetMapping(value = MappingConstant.GET_ALL_USERS_BY_EMAIL)
	public @ResponseBody List<RegisterEntity> getallUsers(@PathVariable("email") String email) {
		return registerService.getAllUsers(email);
	}

	@GetMapping(value = MappingConstant.GET_ALL_USERS_BY_EMAIL_NAME)
	public @ResponseBody List<RegisterEntity> getallUsers(@PathVariable("email") String email, @PathVariable("name") String name) {
		return registerService.getAllUsers(email, name);
	}

	@GetMapping(value = MappingConstant.GET_ALL_USERS_BY_DATALIST)
	public @ResponseBody List<RegisterEntity> getallUsers(@RequestBody List<String> dataList) {
		return registerService.getAllUsers(dataList);
	}
}
