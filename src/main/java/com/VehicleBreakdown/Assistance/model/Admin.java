package com.VehicleBreakdown.Assistance.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Admin {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
	@NotBlank 
	private String username;
    
	@NotBlank 
	private String password;
    
	@NotNull 
	private boolean loggedIn;
    
	public Admin() {
		
	}
	
	public Admin(@NotBlank String username, @NotBlank String password) {
		this.username = username;
		this.password = password;
		this.loggedIn = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
	    if (!(o instanceof Admin)) return false;
	    Admin admin = (Admin) o;
	    return Objects.equals(username, admin.username) && Objects.equals(password, admin.password);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(id, username, password, loggedIn);
	}
	    
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", loggedIn=" + loggedIn + "]";
	}
}
