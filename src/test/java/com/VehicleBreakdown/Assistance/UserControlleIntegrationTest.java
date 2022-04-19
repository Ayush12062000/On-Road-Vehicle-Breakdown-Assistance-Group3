package com.VehicleBreakdown.Assistance;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.VehicleBreakdown.Assistance.model.AssistanceRequired;
import com.VehicleBreakdown.Assistance.model.Mechanic;
import com.VehicleBreakdown.Assistance.model.User;
import com.VehicleBreakdown.Assistance.repository.AssistanceRequiredRepository;
import com.VehicleBreakdown.Assistance.repository.MechanicRepository;

@SpringBootTest(classes = OnRoadVehicleBreakdownAssistanceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControlleIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private AssistanceRequiredRepository assistanceRequiredRepository;
	
	@Autowired
	private MechanicRepository mechanicRepository;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() 
	{
		return "http://localhost:"+port+"/user";
	}
	
	@Test
	void testUserRegistration() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("userName","Pinky");
		req_payload.put("emailId", "pinky@gmail.com");
		req_payload.put("phoneNumber", 1675188976);
		req_payload.put("userPassword", "pink1234");
		req_payload.put("loggedIn", false);
		

		HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/register", request, String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
	void testLoginUser() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		headers.setAll(map);
		Map req_payload = new HashMap();
		req_payload.put("emailId", "pinky@gmail.com");
		req_payload.put("userPassword", "pink1234");
		HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
		ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/login", request, String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
	void testLogoutUser()
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		headers.setAll(map);
		Map req_payload = new HashMap();
		req_payload.put("emailId", "pinky@gmail.com");
		req_payload.put("userPassword", "pink1234");
		HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
		ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/logout", request, String.class);
		assertNotNull(response.getBody());
		
	}
	
	@Test
	void testSearchMechanicByLocation()
	{	
	String location="Malad";
	HttpHeaders headers = new HttpHeaders();
	HttpEntity<List<Mechanic>> entity = new HttpEntity<List<Mechanic>>(null, headers);
	ResponseEntity<List> getResponse = restTemplate.exchange(getRootUrl()+"//searchMechanic/"+location, HttpMethod.GET, entity, List.class);
	System.out.println(getResponse);
	assertEquals(200, getResponse.getStatusCodeValue());
	}
	
	@Test
	void testaddRequest()
	{
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("assistanceType","Tire Puncture");
		req_payload.put("userId", 23);
		req_payload.put("mechanicId",25);
		req_payload.put("location", "Malad");
		
		

		HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/addRequest", request, String.class);
		assertNotNull(response.getBody());
	}
	@Test
	void testGiveFeedback()
	{
		Long mechId=25L;
		Long uId=22L;
		AssistanceRequired assistanceRequired= assistanceRequiredRepository.findByUserIdAndMechanicId(uId,mechId);
		Mechanic mechanic=mechanicRepository.findByMechanicId(mechId);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("userId", 22);
		req_payload.put("mechanicId",25);
		req_payload.put("feedbackMessage", "very nice service");
		req_payload.put("ratings",4);
		req_payload.put("assistanceId",26);
		//req_payload.put(mechanic, mechanic);
		System.out.println(req_payload);
		
		
		

		HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

		ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/giveFeedback/"+mechId+"/"+uId, request, String.class);
		assertNotNull(response.getBody());
		
	}
	
	@Test
	void testGetAll()
	{
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> getResponse = restTemplate.exchange(getRootUrl()+"/all", HttpMethod.GET, entity, String.class);
		System.out.println(getResponse);
		assertEquals(200, getResponse.getStatusCodeValue());
	}
	
	@Test
	void testGetUserById()
	{
		Long id=52L;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> getResponse = restTemplate.exchange(getRootUrl()+"/byid/"+id, HttpMethod.GET, entity, String.class);
		System.out.println(getResponse);
		assertEquals(200, getResponse.getStatusCodeValue());
		
	}
	
	@Test
	void testGetUserByEmailId()
	{
		String emailId="pantu@gmail.com";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> getResponse = restTemplate.exchange(getRootUrl()+"/byemail/"+emailId, HttpMethod.GET, entity, String.class);
		System.out.println(getResponse);
		assertEquals(200, getResponse.getStatusCodeValue());
		
	}
	
	@Test
	public void testUpdateUser()
	{
		long id=22;
		User user = restTemplate.getForObject(getRootUrl()+"/byid/"+id, User.class);
		user.setEmailId("Bunty@gmail.com");
		user.setPhoneNumber(9998887776L);
		user.setUserName("Bunty");
		user.setUserPassword("Bunty@123");
		
		
		restTemplate.put(getRootUrl()+"/update/byid/"+id, user);
		
		User updatedUser =  restTemplate.getForObject(getRootUrl()+"/byid/"+id, User.class);
		
		assertNotNull(updatedUser);
		assertEquals(user.getEmailId(), updatedUser.getEmailId());
	}
	
	
	

}
