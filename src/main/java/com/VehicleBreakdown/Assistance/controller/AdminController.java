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
    public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin newAdmin) {
        List<Admin> admins = adminRepository.findAll();
        System.out.println("New admin: " + newAdmin.toString());
        for (Admin admin : admins) {
            System.out.println("Registered admin: " + newAdmin.toString());
            if (admin.equals(newAdmin)) {
                //System.out.println("Admin Already exists!");
                System.out.println( Status.USER_ALREADY_EXISTS);
                return ResponseEntity.ok().body(newAdmin);
            }
        }
        adminRepository.save(newAdmin);
        System.out.println(Status.SUCCESS);
        return ResponseEntity.ok().body(newAdmin);
    }
    
    @PostMapping("/login")
    public Status loginAdmin(@Valid @RequestBody Admin admin) throws AdminNotFoundException {
        List<Admin> admins = adminRepository.findAll();
        for (Admin other : admins) {
            if (other.equals(admin)) {
            	Admin adm =  adminService.getAdminByUsername(admin.getUsername()).orElseThrow(()->new AdminNotFoundException("No Admin Found with this Username: "+admin.getUsername()));
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
            	adm.setUsername(admin.getUsername());
            	adm.setPassword(admin.getPassword());
            	adm.setLoggedIn(false);
            	adminService.updateAdmin(adm);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
	
	@GetMapping("/users/all")
	public List<User> getAllUsers(){
		return adminService.getAllUsers();
	}
}
