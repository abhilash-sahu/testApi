package com.learning.testApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.testApi.entity.Employee;
import com.learning.testApi.repository.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo employeeRepo;

	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
		
	}

	public Optional<Employee> findEmployeeById(int id) {
		return employeeRepo.findById(id);
	}

	public void deleteEmployee(int id) {
		employeeRepo.deleteById(id);
	}

}
