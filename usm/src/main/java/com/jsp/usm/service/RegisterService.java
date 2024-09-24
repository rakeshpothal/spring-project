package com.jsp.usm.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jsp.usm.dto.AppResponseDto;
import com.jsp.usm.dto.RegisterDto;
import com.jsp.usm.dto.SmsDto;
import com.jsp.usm.entity.RegisterEntity;

public interface RegisterService {
	public List<RegisterEntity> getUsers();
	public RegisterEntity getUserByIdValue(long id);
	public int getdataCount();
	
	public void saveUser(RegisterDto registerDto);
	
	public List<RegisterEntity> getUserByCity(String city);
	public List<RegisterEntity> getuserByCityAndName(String city, String name);
	
	public List<RegisterEntity> getUserByCity(List<String> cityList);
	
	public Page<RegisterEntity> getUsers(int pageNumber, int pageSize,String sortingField, String SortingOrder);
	
	public AppResponseDto sendSms(SmsDto smsDto);
	
	public List<RegisterEntity> getAllUsers(); 
	public List<RegisterEntity> getAllUsers(String email);
	public List<RegisterEntity> getAllUsers(String email,String name);
	public List<RegisterEntity> getAllUsers(List<String> dataList);
}
