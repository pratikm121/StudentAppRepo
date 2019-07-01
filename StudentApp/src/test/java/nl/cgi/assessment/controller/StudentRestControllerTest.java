package nl.cgi.assessment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.stubbing.OngoingStubbing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import nl.cgi.assessment.dao.StudentRepository;
import nl.cgi.assessment.model.Student;
import nl.cgi.assessment.service.StudentService;
import nl.cgi.assessment.util.GenerateStub;
import nl.cgi.assessment.util.JsonConvertor;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentRestControllerTest {
	
	public static final Logger logger = LoggerFactory.getLogger(StudentRestControllerTest.class);
	
	@LocalServerPort
    private int port = 9000;
	
	@Value("${data.not.found}")
	private String noDataFound;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
	private StudentRepository repo;
	
	@Autowired
	private StudentService service;
	
	private static Optional<Student> nonExistingUser;	
	private static Optional<Student> existingUser;
	private static List<Student> existingUserList;
	
	 @BeforeAll
	 public static void setData() {
		 logger.warn("############Init Done####################");
		 nonExistingUser = GenerateStub.getNonExistingStudent(); 
		 existingUser =  GenerateStub.getExistingStudent(); 
		 existingUserList = GenerateStub.getStudentList();
	 }
	 
	 @Test
	 public void findAll_checkWhenStudentListIsNull() throws Exception {
	  	final String baseUrl = "http://localhost:"+port+"/cgi/";
	  	final int responseCode = 200;
	   	when(service.findAll()).thenReturn(null);
	    	
	   	ResponseEntity<String> response = restTemplate.getForEntity(new URL(baseUrl + "student/").toString(), String.class);
	    assertEquals(noDataFound, response.getBody());
	    assertEquals(responseCode, response.getStatusCodeValue());
	       	
	 }
	 
	 @Test
	 public void findAll_checkWhenStudentListIsNotNull() throws Exception {
	  	final String baseUrl = "http://localhost:"+port+"/cgi/";
	  	final int responseCode = 200;
	   	when(service.findAll()).thenReturn(existingUserList);
	    	
	   	ResponseEntity<String> response = restTemplate.getForEntity(new URL(baseUrl + "student/").toString(), String.class);
	   	assertNotNull(response.getBody());
	    assertEquals(responseCode, response.getStatusCodeValue());	       	
	 }
	 
	 @Test
	 public void findById_WhenIdExists() throws Exception {
	  	final String baseUrl = "http://localhost:"+port+"/cgi/";
	  	final int responseCode = 200;
	   	when(service.findById(new BigDecimal(1))).thenReturn(existingUser);
	    	
	   	ResponseEntity<Student> response = restTemplate.getForEntity(new URL(baseUrl + "student/1").toString(), Student.class);
	   	assertNotNull(response.getBody());
	   	assertEquals(new BigDecimal(1),response.getBody().getId());
	   	assertEquals("Pratik",response.getBody().getFirstName());
	   	assertEquals("Mehta",response.getBody().getLastName());
	   	assertEquals("14 Teilingerstraat 3032 AN Rotterdam Netherland",response.getBody().getAddress());
	    assertEquals(responseCode, response.getStatusCodeValue());	       	
	 }
	 
	 @Test
	 public void findById_WhenIdDoesNotExist() throws Exception {
	  	final String baseUrl = "http://localhost:"+port+"/cgi/";
	  	final int responseCode = 400;
	   	when(service.findById(new BigDecimal(100))).thenReturn(null);
	    	
	   	ResponseEntity<Student> response = restTemplate.getForEntity(new URL(baseUrl + "student/100").toString(), Student.class);
	   	
	   	assertNull(response.getBody());
	   	assertEquals(responseCode, response.getStatusCodeValue());	       	
	 }
	 
	 
	 @Test
	 public void create_whenStudentDoesNotExists() throws RestClientException, MalformedURLException {
		 
		 final String baseUrl = "http://localhost:"+port+"/cgi/";
		 final int responseCode = 200;
		 when(service.findByName(anyString(),anyString())).thenReturn(null);
		 Student student = nonExistingUser.get();
		 
		 HttpHeaders headers = new HttpHeaders();
		 HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

		 ResponseEntity<String> response = restTemplate.exchange(new URL(baseUrl + "student/").toString(),
					HttpMethod.POST, entity, String.class);
		 
		 assertNotNull(response.getBody());
		 assertEquals("Details of Serena has been created successfully.", "Details of "+student.getFirstName() + " has been created successfully.");
		 assertEquals(responseCode, response.getStatusCodeValue());	
			
	 }
	 
	@Test
	 public void create_whenStudentExists() throws RestClientException, MalformedURLException {
		 
		 final String baseUrl = "http://localhost:"+port+"/cgi/";
		 final int responseCode = 200;
		 when(service.findByName(anyString(),anyString())).thenReturn(existingUser.get());
		 Student student = existingUser.get();
		 
		 HttpHeaders headers = new HttpHeaders();
		 HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

		 ResponseEntity<String> response = restTemplate.exchange(new URL(baseUrl + "student/").toString(),
					HttpMethod.POST, entity, String.class);
		 
		 assertNotNull(response.getBody());
		 assertEquals("Details of Pratik is already stored in the database.", "Details of "+student.getFirstName() + " is already stored in the database.");
		 assertEquals(responseCode, response.getStatusCodeValue());	
			
	 }
	 
	 
	 @Test
	 public void delete_whenStudentExists() throws RestClientException, MalformedURLException {
		 
		 final String baseUrl = "http://localhost:"+port+"/cgi/";
		 final int responseCode = 200;
		 when(service.findByName(anyString(),anyString())).thenReturn(existingUser.get());
		 Student student = existingUser.get();
		 
		 HttpHeaders headers = new HttpHeaders();
		 HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

		 ResponseEntity<String> response = restTemplate.exchange(new URL(baseUrl + "student/1").toString(),
					HttpMethod.DELETE, entity, String.class);
		 
		 assertNotNull(response.getBody());
		 assertEquals("Id 1  has been deleted successfully.", "Id "+student.getId() + "  has been deleted successfully.");
		 assertEquals(responseCode, response.getStatusCodeValue());	
			
	 }
	 
	 @Test
	 public void delete_whenStudentDoesNotExists() throws RestClientException, MalformedURLException {
		 
		 final String baseUrl = "http://localhost:"+port+"/cgi/";
		 final int responseCode = 200;
		 when(service.findByName(anyString(),anyString())).thenReturn(null);
		 Student student = nonExistingUser.get();
		 
		 HttpHeaders headers = new HttpHeaders();
		 HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

		 ResponseEntity<String> response = restTemplate.exchange(new URL(baseUrl + "student/1").toString(),
					HttpMethod.DELETE, entity, String.class);
		 
		 assertNotNull(response.getBody());
		 assertEquals("Id 100  is not existed", "Id "+student.getId() + "  is not existed");
		 assertEquals(responseCode, response.getStatusCodeValue());	
			
	 }
	 
	 
	 @Test
	 public void update_whenStudentExists() throws RestClientException, MalformedURLException {
		 
		 final String baseUrl = "http://localhost:"+port+"/cgi/";
		 final int responseCode = 200;
		 when(service.findByName(anyString(),anyString())).thenReturn(existingUser.get());
		 Student student = existingUser.get();
		 student.setAddress("14 Teilingerstraat 3032 AN Rotterdam Netherland");
		 
		 HttpHeaders headers = new HttpHeaders();
		 HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

		 ResponseEntity<String> response = restTemplate.exchange(new URL(baseUrl + "student").toString(),
					HttpMethod.PUT, entity, String.class);
		 
		 assertEquals(responseCode, response.getStatusCodeValue());
			
	 }
	 
	 @Test
	 public void update_whenStudentDoesNotExists() throws RestClientException, MalformedURLException {
		 
		 final String baseUrl = "http://localhost:"+port+"/cgi/";
		 final int responseCode = 200;
		 when(service.findByName(anyString(),anyString())).thenReturn(null);
		 Student student = nonExistingUser.get();
		 
		 HttpHeaders headers = new HttpHeaders();
		 HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

		 ResponseEntity<String> response = restTemplate.exchange(new URL(baseUrl + "student").toString(),
					HttpMethod.PUT, entity, String.class);
		 
		 assertEquals(responseCode, response.getStatusCodeValue());	
			
	 }

}
