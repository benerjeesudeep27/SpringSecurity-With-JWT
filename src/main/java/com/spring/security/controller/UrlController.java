package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.Employee;
import com.spring.security.service.EmployeeService;
import com.spring.security.service.JwtService;

@RestController
@RequestMapping("/api")
public class UrlController {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwt;

	// Registration
	@PostMapping("/register")
	public String registerHere(@RequestBody Employee emp) {
		String encodedPass = passwordEncoder.encode(emp.getPassword());
		emp.setPassword(encodedPass);
		boolean flag = employeeService.saveEmployee(emp);
		if (flag) {
			return "Employee Registed Successfully";
		} else {
			return "Can't Register Employee";
		}
	}

	// Login
	@PostMapping("/login")
	public String loginHere(@RequestBody Employee employee) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(employee.getUsername(),
				employee.getPassword());
		Authentication authentication = authManager.authenticate(token);
		if (authentication.isAuthenticated()) {
			return jwt.generateToken(employee.getUsername());
		} else {
			return "Invalid Credentials";
		}
	}

	// Welcome (Secured)
	@GetMapping("/welcome")
	public String welcomeMsg() {
		return "Welcome to Spring Security with JWT";
	}
	
	// Greet (Secured)
	@GetMapping("/greet")
	public String greetMsg() {
		return "Good Morning Everyone";
	}
}
