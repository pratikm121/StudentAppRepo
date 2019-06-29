package nl.cgi.assessment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.cgi.assessment.dao.StudentRepository;
import nl.cgi.assessment.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository repo;
	
	public Student findByName(String firstName, String lastName) {
		return repo.findFirstByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Student> findAll() {
		return repo.findAll();
	}

	@Override
	public Student save(Student student) {
		return repo.save(student);
	}

	@Override
	public Student update(Student student) {
		return repo.save(student);
	}

	@Override
	public Optional<Student> findById(BigDecimal id) {
		return repo.findById(id);
	}

	@Override
	public void deleteById(BigDecimal id) {
		repo.deleteById(id);		
	}

}
