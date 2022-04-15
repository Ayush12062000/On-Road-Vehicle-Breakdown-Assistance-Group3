package com.VehicleBreakdown.Assistance.model;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

public class UserLogin {
	@NotEmpty(message="Email is required")
	private String emailId;
	
	@NotEmpty(message="Password is required")
	private String userPassword;
	
	public UserLogin() {
		
	}

	public UserLogin(String emailId, String userPassword) {
		this.emailId = emailId;
		this.userPassword = userPassword;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
	    if (!(o instanceof UserLogin)) return false;
	    UserLogin user = (UserLogin) o;
	    return Objects.equals(emailId, user.emailId) && Objects.equals(userPassword, user.userPassword);
	}
	
	@Override
	public String toString() {
		return "UserLogin [emailId=" + emailId + ", userPassword=" + userPassword + "]";
	}
}
