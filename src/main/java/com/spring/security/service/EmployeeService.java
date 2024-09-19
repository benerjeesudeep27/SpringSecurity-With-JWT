package com.spring.security.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.entity.Employee;
import com.spring.security.repository.EmployeeRepository;

@Service
public class EmployeeService implements UserDetailsService {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee emp = repository.findByUsername(username);
		User user = new User(emp.getUsername(), emp.getPassword(), Collections.emptyList());
		return user;
	}

	public boolean saveEmployee(Employee emp) {
		boolean status = false;
		Employee employee = repository.save(emp);
		if (employee != null)
			status = true;
		else
			status = false;
		return status;
	}
}
