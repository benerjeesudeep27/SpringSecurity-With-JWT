package com.spring.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.spring.security.entity.Employee;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	Employee findByUsername(String username);
}
