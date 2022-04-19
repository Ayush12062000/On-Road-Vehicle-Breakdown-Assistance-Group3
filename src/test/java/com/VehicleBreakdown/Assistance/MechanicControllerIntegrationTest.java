package com.VehicleBreakdown.Assistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import com.VehicleBreakdown.Assistance.model.Feedback;
import com.VehicleBreakdown.Assistance.model.Mechanic;

@SpringBootTest(classes = OnRoadVehicleBreakdownAssistanceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MechanicControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() 
	{
		return "http://localhost:"+port+"/mechanic";
	}
	
	@Test
	public void testMechanicRegistration()
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("mechanicName","Pantu");
		req_payload.put("mechanicEmailId", "pantu@gmail.com");
		req_payload.put("mechanicPhoneNumber", 1423188976);
		req_payload.put("mechanicPassword", "pantu1234");
		req_payload.put("mechanicLocation", "Malad");
		req_payload.put("loggedIn", false);
		req_payload.put("allowed", true);

		HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/register", request, String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testUpdateMechanic()
	{
		long id=70;
		Mechanic mechanic = restTemplate.getForObject(getRootUrl()+"/bymechanicid/"+id, Mechanic.class);
		mechanic.setMechanicName("Harshu");
		mechanic.setMechanicEmailId("harshu@hotmail.com");
		mechanic.setMechanicPassword("harshu1234");
		
		restTemplate.put(getRootUrl()+"/update/bymechanicid/"+id, mechanic);
		
		Mechanic updatedMechanic =  restTemplate.getForObject(getRootUrl()+"/bymechanicid/"+id, Mechanic.class);
		
		assertNotNull(updatedMechanic);
		assertEquals(mechanic.getMechanicEmailId(), updatedMechanic.getMechanicEmailId());
	}
	
	@Test
	public void testViewFeedback()
	{
		long id = 25;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<List<Feedback>> entity = new HttpEntity<List<Feedback>>(null, headers);
		ResponseEntity<List> getResponse = restTemplate.exchange(getRootUrl()+"/viewFeedback/"+id, HttpMethod.GET, entity, List.class);
		System.out.println(getResponse);
		assertEquals(200, getResponse.getStatusCodeValue());
	}
	
	@Test
	public void testViewRequest()
	{
		long id = 59;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<List<Feedback>> entity = new HttpEntity<List<Feedback>>(null, headers);
		ResponseEntity<List> getResponse = restTemplate.exchange(getRootUrl()+"/viewRequest/"+id, HttpMethod.GET, entity, List.class);
		System.out.println(getResponse);
		assertEquals(200, getResponse.getStatusCodeValue());
	}
	
	@Test
	public void testGetMechanicByMechanicEmailId()
	{
		Mechanic mechanic = restTemplate.getForObject(getRootUrl()+"/bymechanicemail/jaggu@gmail.com", Mechanic.class);
		assertNotNull(mechanic);
	}
	
	@Test
	public void testGetMechanicByMechanicId()
	{
		Mechanic mechanic = restTemplate.getForObject(getRootUrl()+"/bymechanicid/60", Mechanic.class);
		assertNotNull(mechanic);
	}
	
	@Test
	public void testLoginMechanic()
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("mechanicEmailId", "harshu@hotmail.com");
        req_payload.put("mechanicPassword", "harshu1234");
        
        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/login", request, String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testLogoutMechanic()
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("mechanicEmailId", "lucky@gmail.com");
        req_payload.put("mechanicPassword", "lucky1234");
        
        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/logout", request, String.class);
		assertNotNull(response.getBody());
	}
	
	
}
