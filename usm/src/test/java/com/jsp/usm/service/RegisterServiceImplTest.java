package com.jsp.usm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.jsp.usm.dto.RegisterDto;
import com.jsp.usm.entity.RegisterEntity;
import com.jsp.usm.repository.MySqlRepository;
import com.jsp.usm.repository.RegisterRepository;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceImplTest {

	@Mock
	private RegisterRepository registerRepository;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private MySqlRepository mySqlRepository;

	@InjectMocks
	private RegisterServiceImpl registerServiceImpl;

	@Test
	public void testGetUsers() {
		List<RegisterEntity> list = new ArrayList<RegisterEntity>();
		RegisterEntity registerEntity = new RegisterEntity();
		list.add(registerEntity);

		when(registerRepository.findAll()).thenReturn(list);
		List<RegisterEntity> userList = registerServiceImpl.getUsers();
		assertEquals(list, userList);
	}

	@Test
	public void testGetUsersByCity() {
		List<RegisterEntity> list = new ArrayList<RegisterEntity>();
		RegisterEntity registerEntity = new RegisterEntity();
		list.add(registerEntity);

		when(registerRepository.getUserByCity(anyString())).thenReturn(list);
		List<RegisterEntity> usersByCityList = registerServiceImpl.getUserByCity(anyString());
		assertEquals(list, usersByCityList);

	}
	
	@Test
	public void testGetUsersByCityAndName() {
		List<RegisterEntity> list = new ArrayList<RegisterEntity>();
		RegisterEntity registerEntity = new RegisterEntity();
		list.add(registerEntity);
		
		when(registerRepository.getUserByCityAndName(anyString(), anyString())).thenReturn(list);
		List<RegisterEntity> getuserByCityAndName = registerServiceImpl.getuserByCityAndName(anyString(), anyString());
		assertEquals(list, getuserByCityAndName);
	}
	

	@Test
	public void testSaveUser() {
		RegisterDto registerDto = new RegisterDto();
//		doNothing().when(registerRepository.save(registerDto));
		registerServiceImpl.saveUser(registerDto);
	}
}
