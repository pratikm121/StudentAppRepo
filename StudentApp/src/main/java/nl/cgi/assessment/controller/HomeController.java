package nl.cgi.assessment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Value("${welcome.message}")
	private String welcome;
	
	@GetMapping("/")
	public String showLoginView(Model model) {
	  model.addAttribute("welcome", welcome);
	  return "home";
	}
}
