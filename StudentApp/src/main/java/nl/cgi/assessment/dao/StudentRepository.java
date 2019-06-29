package nl.cgi.assessment.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.cgi.assessment.model.Student;

public interface StudentRepository extends JpaRepository<Student, BigDecimal> {
	
	public Student findFirstByFirstNameAndLastName(String firstName, String lastName);

}
