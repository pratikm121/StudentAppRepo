package nl.cgi.assessment.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	public StudentRestController (StudentService theService) {
		service = theService;
	}
	
	@GetMapping("/student")
	public ResponseEntity<Object> findAll() {
		logger.debug("getting students");
		List<Student> studentList = service.findAll();
		if(studentList.isEmpty()) {
			return ResponseEntity.ok("No data found.");
		}else {
			return ResponseEntity.ok(studentList);
		}
        
    }
	
	@PostMapping("/student")
	public ResponseEntity<Object> create(@RequestBody Student student) {
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
        if (!student.isPresent()) {
            logger.warn("Id " + id + " does not exist");
            ResponseEntity.badRequest().build();
        }
        logger.debug("Got student = "+student.get());

        return ResponseEntity.ok(student.get());
    }
	
	@PutMapping("/student")
    public ResponseEntity<Student> update(@RequestBody Student student) {
		logger.debug("Updating student = "+student.toString());
		Student data = service.findByName(student.getFirstName(), student.getLastName());
		
		if (data.getFirstName() ==null) {
            logger.warn("No student with name " + student.getFirstName() + " "+ student.getLastName() 
            + " exists in our record.");
            ResponseEntity.badRequest().build();
        }else {
        	student.setId(data.getId());
        }

        return ResponseEntity.ok(service.save(student));
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
