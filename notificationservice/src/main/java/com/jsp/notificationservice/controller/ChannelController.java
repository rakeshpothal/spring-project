package com.jsp.notificationservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.notificationservice.constant.ConstantMapping;
import com.jsp.notificationservice.dto.AppResponseDto;
import com.jsp.notificationservice.dto.SmsDto;

@RestController
public class ChannelController {
	
	@PostMapping(value = ConstantMapping.SEND_SMS)
	public AppResponseDto sendSms(@RequestBody SmsDto smsDto) {
		System.out.println(smsDto);
		return new AppResponseDto("Success", "200", null, null);
	}
}
