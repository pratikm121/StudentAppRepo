package nl.cgi.assessment.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nl.cgi.assessment.model.Student;
import nl.cgi.assessment.service.StudentService;

@RestController
public class StudentRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(StudentRestController.class);
	
	@Autowired
	private StudentService service;
	
	@Value("${data.not.found}")
	private String noDataFound;
	
	
	@Autowired
	public StudentRestController (StudentService theService) {
		service = theService;
	}
	
	@GetMapping("/student")
	public ResponseEntity<Object> findAll() {
		logger.debug("getting students");
		List<Student> studentList = service.findAll();
		if(studentList == null ) {
			return ResponseEntity.ok(noDataFound);
		}else {
			return ResponseEntity.ok(studentList);
		}
        
    }
	
	@PostMapping("/student")
	public ResponseEntity<String> create(@RequestBody Student student) {
		logger.debug("Creatingf student = "+student.toString());
		Student data = service.findByName(student.getFirstName(), student.getLastName());
		if(data !=null) {
			return ResponseEntity.ok("Details of "+student.getFirstName() + " is already stored in the database.");
		}else {
			service.save(student);
			return ResponseEntity.ok("Details of "+student.getFirstName() + " has been created successfully.");
		}
	}
	
	@GetMapping("/student/{id}")
    public ResponseEntity<Student> findById(@PathVariable BigDecimal id) {
		logger.debug("finding studentId = "+id);
        Optional<Student> student = service.findById(id);
        if (student ==null) {
            logger.warn("Id " + id + " does not exist");
            return ResponseEntity.badRequest().build();
        }else {
        	return ResponseEntity.ok(student.get());
        }
    }
	
	@PutMapping("/student")
    public ResponseEntity<String> update(@RequestBody Student student) {
		logger.debug("Updating student = "+student.toString());
		Student data = service.findByName(student.getFirstName(), student.getLastName());
		
		if (data ==null) {
            logger.warn("No student with name " + student.getFirstName() + " "+ student.getLastName() 
            + " exists in our record.");
            return ResponseEntity.ok("No student with name " + student.getFirstName() + " "+ student.getLastName() 
            + " exists in our record.");
        }else {
        	student.setId(data.getId());
        	service.save(student);
        	return ResponseEntity.ok("Details of "+student.getFirstName() + " has been updated successfully.");
        }
		
    }
	
	@DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable BigDecimal id) {
		logger.debug("Deleting studentId = "+id);
        if (!service.findById(id).isPresent()) {
            logger.warn("Id " + id + " is not existed");
            return ResponseEntity.ok("Id " + id + " is not existed");
        }else {
        	service.deleteById(id);
        	return ResponseEntity.ok("Id " + id + " has been deleted successfully.");
        }
    }

}
