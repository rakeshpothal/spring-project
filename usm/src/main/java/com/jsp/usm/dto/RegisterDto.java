package com.jsp.usm.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RegisterDto implements Serializable {
	private String name;
	private String email;
	private String contact;
	private String city;
	private String pincode;


}
