package com.learning.testApi.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@Api(value = "employee", description = "Contains information of the employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/getEmployees")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employeeList = employeeService.getEmployees();
		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getEmployee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") int id) {
		Employee employee = employeeService.findEmployeeById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@PostMapping("/saveEmployee")
	public ResponseEntity<List<Employee>> saveEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		List<Employee> employeeList = employeeService.getEmployees();
		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@PutMapping(value = "/updateEmployee/{id}")
	public ResponseEntity<List<Employee>> updateEmployee(@PathVariable(value = "id") int id, @RequestBody Employee employee) {
		employeeService.findEmployeeById(id);
		employee.setId(id);
		employeeService.saveEmployee(employee);
		List<Employee> employeeList = employeeService.getEmployees();
		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@GetMapping(value = "/deleteEmployee/{id}")
	public ResponseEntity<List<Employee>> deleteEmployee(@PathVariable(value = "id") int id) {
		employeeService.findEmployeeById(id);
		List<Employee> employeeList = employeeService.getEmployees();
		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@PatchMapping(value = "/patchEmployee/{id}")
	public ResponseEntity<List<Employee>> patchEmployee(@PathVariable(value = "id") int id, 
			@RequestBody Map<String, Object> employeeMap) {
		Employee employee = employeeService.findEmployeeById(id);
		
		employeeMap.forEach((k, v) -> {
		        Field field = ReflectionUtils.findField(Employee.class, k);
		        field.setAccessible(true);
		        ReflectionUtils.setField(field,employee, v);
		    });
		
		employeeService.saveEmployee(employee);
		List<Employee> employeeList = employeeService.getEmployees();
		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

}
