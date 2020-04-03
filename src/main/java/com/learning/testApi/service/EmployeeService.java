package com.learning.testApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.testApi.entity.Employee;
import com.learning.testApi.exceptionHandling.RecordNotFoundException;
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

	public Employee findEmployeeById(int id) {
		return employeeRepo.findById(id).orElseThrow(()-> new RecordNotFoundException("Invalid employee id : " + id));
	}

	public void deleteEmployee(int id) {
		employeeRepo.deleteById(id);
	}

}
