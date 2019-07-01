package nl.cgi.assessment.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.cgi.assessment.model.Student;

public class GenerateStub {
	
	public static List<Student> getStudentList(){
		List<Student> studentList = new ArrayList<Student>();
		Student student1 = new Student();
		Student student2 = new Student();
		Student student3 = new Student();
		
		student1.setFirstName("Pratik");
		student1.setLastName("Mehta");
		student1.setAddress("13 Teilingerstraat 3032 AN Rotterdam Netherland");
		student1.setId(new BigDecimal(1));
		student1.setDateOfBirth(new Date(System.currentTimeMillis()));
		
		student2.setFirstName("John");
		student2.setLastName("Doe");
		student2.setAddress("1 Scheikade Amsterdam Netherland");
		student2.setId(new BigDecimal(2));
		student2.setDateOfBirth(new Date(System.currentTimeMillis()));
		
		student3.setFirstName("Jair");
		student3.setLastName("Rosales");
		student3.setAddress("Leuwhaven 1067 AD Rotterdam Netherland");
		student3.setId(new BigDecimal(1));
		student3.setDateOfBirth(new Date(System.currentTimeMillis()));
		
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		
		return studentList;
	}
	
	public static Optional<Student> getExistingStudent() {
		Student student1 = new Student();
		Optional<Student> student = Optional.of(student1);
		
		student1.setFirstName("Pratik");
		student1.setLastName("Mehta");
		student1.setAddress("13 Teilingerstraat 3032 AN Rotterdam Netherland");
		student1.setId(new BigDecimal(1));
		student1.setDateOfBirth(new Date(System.currentTimeMillis()));
		
		return student;
	}
	
	
	public static Optional<Student> getNonExistingStudent() {
		Student student1 = new Student();
		Optional<Student> student = Optional.of(student1);
		
		student1.setFirstName("Serena");
		student1.setLastName("Williams");
		student1.setAddress("13 Teilingerstraat 3032 AN Rotterdam Netherland");
		student1.setId(new BigDecimal(100));
		student1.setDateOfBirth(new Date(System.currentTimeMillis()));
		
		return student;
	}

}
