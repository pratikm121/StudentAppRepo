package nl.cgi.assessment.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import nl.cgi.assessment.model.Student;

@Service
public interface StudentService {
	
	public List<Student> findAll();
	public Student findByName(String firstName, String lastName);
	public Student save(Student student);
	public Student update(Student student);
	public Optional<Student> findById(BigDecimal id);
	public void deleteById(BigDecimal id);
}
