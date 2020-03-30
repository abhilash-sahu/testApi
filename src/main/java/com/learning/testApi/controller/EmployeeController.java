package com.learning.testApi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.testApi.entity.Employee;
import com.learning.testApi.service.EmployeeService;

import io.swagger.annotations.Api;

@RestController 
@RequestMapping("/employee")
@Api(value="employee", description="Contains information of the employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/getEmployees")
	public List<Employee> getEmployees() {
        List<Employee> employeeList = employeeService.getEmployees();
        return employeeList;
	}
	
	@GetMapping(value = "/getEmployee/{id}")
	public Employee getEmployee(@PathVariable(value = "id") int id) {
		Optional<Employee> isEmployeeExist = employeeService.findEmployeeById(id);
		if (!isEmployeeExist.isPresent()) {
			return null;
		}else {
			return isEmployeeExist.get();
		}
	}
	
	@PostMapping("/saveEmployee")
	public List<Employee> saveEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
        List<Employee> employeeList = employeeService.getEmployees();
        return employeeList;
	}
	
	@PutMapping(value = "/updateEmployee/{id}")
	public List<Employee> updateEmployee(@PathVariable(value = "id") int id, @RequestBody Employee employee) {
		Optional<Employee> isEmployeeExist = employeeService.findEmployeeById(id);
		if (!isEmployeeExist.isPresent()) {
			return null;
		}
		employee.setId(id);
		employeeService.saveEmployee(employee);
		List<Employee> employeeList = employeeService.getEmployees();
		return employeeList;
	}
	
	@GetMapping(value = "/deleteEmployee/{id}")
	public List<Employee> deleteEmployee(@PathVariable(value = "id") int id) {
		Optional<Employee> isEmployeeExist = employeeService.findEmployeeById(id);
		if (isEmployeeExist.isPresent()) {
			employeeService.deleteEmployee(id);

		}
		List<Employee> employeeList = employeeService.getEmployees();
		return employeeList;
	}
	
	
	
}
