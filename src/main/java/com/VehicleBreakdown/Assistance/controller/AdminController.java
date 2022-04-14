package com.VehicleBreakdown.Assistance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VehicleBreakdown.Assistance.exception.AdminNotFoundException;
import com.VehicleBreakdown.Assistance.exception.InvalidLoginException;
import com.VehicleBreakdown.Assistance.model.Admin;
import com.VehicleBreakdown.Assistance.model.Status;
import com.VehicleBreakdown.Assistance.model.User;
import com.VehicleBreakdown.Assistance.repository.AdminRepository;
import com.VehicleBreakdown.Assistance.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	public AdminService adminService;
	
	@Autowired
	public AdminRepository adminRepository;
	
	@PostMapping("/register")
    public Status registerAdmin(@Valid @RequestBody Admin newAdmin) {
        List<Admin> admins = adminRepository.findAll();
        for (Admin admin : admins) {
            if (admin.equals(newAdmin)) {
                return Status.USER_ALREADY_EXISTS;
            }
        }
        adminRepository.save(newAdmin);
        return Status.SUCCESS;
        
    }
    
    @PostMapping("/login")
    public Status loginAdmin(@Valid @RequestBody Admin admin) throws AdminNotFoundException {
        List<Admin> admins = adminRepository.findAll();
        for (Admin other : admins) {
            if (other.equals(admin)) {
            	Admin adm =  adminService.getAdminByUsername(admin.getUsername()).orElseThrow(()->new AdminNotFoundException("No Admin Found with this Username: "+admin.getUsername()));
            	if(adm.isLoggedIn())
            		return Status.FAILURE;
            	adm.setUsername(admin.getUsername());
            	adm.setPassword(admin.getPassword());
            	adm.setLoggedIn(true);
            	adminService.updateAdmin(adm);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    
    @PostMapping("/logout")
    public Status logAdminOut(@Valid @RequestBody Admin admin) throws AdminNotFoundException {
        List<Admin> admins = adminRepository.findAll();
        for (Admin other : admins) {
            if (other.equals(admin)) {
            	Admin adm =  adminService.getAdminByUsername(admin.getUsername()).orElseThrow(()->new AdminNotFoundException("No Admin Found with this Username: "+admin.getUsername()));
            	if(!adm.isLoggedIn())
            		return Status.FAILURE;
            	adm.setUsername(admin.getUsername());
            	adm.setPassword(admin.getPassword());
            	adm.setLoggedIn(false);
            	adminService.updateAdmin(adm);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
	
    @GetMapping("/login/showusers")
	public List<User> getAllUsers(@Valid @RequestBody Admin admin) throws InvalidLoginException, AdminNotFoundException{
    	List<Admin> admins = adminRepository.findAll();
        for (Admin other : admins) {
            if (other.equals(admin)) {
            	Admin adm =  adminService.getAdminByUsername(admin.getUsername()).orElseThrow(()->new AdminNotFoundException("No Admin Found with this Username: "+admin.getUsername()));
            	if(adm.isLoggedIn())
            		return adminService.getAllUsers();
            	else 
            		throw new InvalidLoginException("Login Required");
            }
            else
            	throw new InvalidLoginException("Invalid Login Credentials");
        }
        throw new AdminNotFoundException("No Database found");
	}
}
