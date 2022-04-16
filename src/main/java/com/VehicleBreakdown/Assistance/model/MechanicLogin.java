
package com.VehicleBreakdown.Assistance.model;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

public class MechanicLogin {
	@NotEmpty(message="Email is required")
	private String mechanicEmailId;
	
	@NotEmpty(message="Password is required")
	private String mechanicPassword;
	
	public MechanicLogin() {
		
	}

	public MechanicLogin(String mechanicEmailId, String mechanicPassword) {
		this.mechanicEmailId = mechanicEmailId;
		this.mechanicPassword = mechanicPassword;
	}

	public String getMechanicEmailId() {
		return mechanicEmailId;
	}

	public void setMechanicEmailId(String mechanicEmailId) {
		this.mechanicEmailId = mechanicEmailId;
	}

	public String getMechanicPassword() {
		return mechanicPassword;
	}

	public void setMechanicPassword(String mechanicPassword) {
		this.mechanicPassword = mechanicPassword;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
	    if (!(obj instanceof MechanicLogin)) return false;
	    MechanicLogin mechanic = (MechanicLogin) obj;
	    return Objects.equals(mechanicEmailId, mechanic.mechanicEmailId) && Objects.equals(mechanicPassword, mechanic.mechanicPassword);
	}
	
	@Override
	public String toString() {
		return "MechanicLogin [mechanicEmailId=" + mechanicEmailId + ", mechanicPassword=" + mechanicPassword + "]";
	}
}
