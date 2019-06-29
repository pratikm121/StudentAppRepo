package nl.cgi.assessment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(DataNotFoundException ex){
		ErrorResponse error = new ErrorResponse();
		error.setErrorMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimestamp(System.currentTimeMillis());
		logger.error("DataNotFoundException called " + ex);
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception ex){
		ErrorResponse error = new ErrorResponse();
		if(MethodArgumentTypeMismatchException.class.equals(ex.getClass())) {
			error.setErrorMessage("Invalid input parameters passed.");
		}else {
			error.setErrorMessage(ex.getMessage());
		}
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimestamp(System.currentTimeMillis());
		logger.error("General handleException called " ,ex);
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}


}
