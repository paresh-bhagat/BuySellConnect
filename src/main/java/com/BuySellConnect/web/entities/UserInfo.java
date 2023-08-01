package com.BuySellConnect.web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="User_Info")
public class UserInfo {
	
	@Id
	@Column(length=20,name="User_Name")
	@Size(min=1,max=20,message="Username between 1 to 20 characters")
	private String user_name;
	
	@Column(length=20,name="User_Password")
	@Size(min=1,max=20,message="Password between 1 to 20 characters")
	private String user_password;
	
	@Column(length=10,name="User_Mobile_Number")
	private String user_mobile_number;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_mobile_number() {
		return user_mobile_number;
	}

	public void setUser_mobile_number(String user_phone_number) {
		this.user_mobile_number = user_phone_number;
	}

	public UserInfo(String user_name, String user_password, String user_phone_number) {
		super();
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_mobile_number = user_phone_number;
	}

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserInfo [user_name=" + user_name + ", user_password=" + user_password + ", user_phone_number="
				+ user_mobile_number + "]";
	}
	
}
