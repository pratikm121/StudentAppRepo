package nl.cgi.assessment.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.cgi.assessment.model.Student;

public class JsonConvertor {
	
	 	/**
		 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.writeValueAsString
		 */
		public static String mapToJson(Object object) throws JsonProcessingException {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(object);
		}
		
		/**
		 * Maps an JSON String into a Map<String, Object>. Uses a Jackson ObjectMapper.readvalue
		 */
		public static  Map<String, Object> jsonToMap(String jsonObject) throws IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(jsonObject, new TypeReference<Map<String,Object>>(){});
		}
		
		/**
		 * Maps an JSON String into a custom POJO Student. Uses a Jackson ObjectMapper.readvalue
		 */
		public static  Student jsonToStudent(String jsonObject) throws IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(jsonObject, new TypeReference<Student>(){});
		}
		
		/**
		 * Maps an JSON String into a custom POJO Student. Uses a Jackson ObjectMapper.readvalue
		 */
		public static  List<Student> jsonToUserProfileList(String jsonObject) throws IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(jsonObject, new TypeReference<List<Student>>(){});
		}
		
}
