package nl.cgi.assessment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentAppStarter {
	
	public static final Logger logger = LoggerFactory.getLogger(StudentAppStarter.class);

	public static void main(String[] args) {
		SpringApplication.run(StudentAppStarter.class, args);
		logger.debug("Loaded");
	}

}
