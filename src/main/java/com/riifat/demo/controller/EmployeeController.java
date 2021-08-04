package com.riifat.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riifat.demo.model.Employee;
import com.riifat.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	
	
	
	//build create Rest Employee ApI
	@PostMapping("/add")
	 public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		
		
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
		 
	 }
	
	//build get all employee
	@GetMapping("/all")
	List<Employee> getAllEmplyees(){
		return employeeService.getAllEmployees();
	}
    
	
	   @GetMapping("{id}")
	   ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
		   
		   return new ResponseEntity<Employee> (employeeService.getEmployeeById(employeeId),HttpStatus.OK);
	   }
	   
	   @PutMapping("{id}")
	   ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
			      @RequestBody  Employee employee  ){
		   
		   return new ResponseEntity<Employee> (employeeService.updateEmployee(employee, id),HttpStatus.OK);
	   }
	   

	   @DeleteMapping("{id}")
	   ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		   
		   employeeService.deleteEmployee(id);
		   return new ResponseEntity<String> (("Delete successfully....."),HttpStatus.OK);
	   }
	   
	   
}
