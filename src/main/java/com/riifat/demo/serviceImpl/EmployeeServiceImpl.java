package com.riifat.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riifat.demo.exception.ResourceNotFoundException;
import com.riifat.demo.model.Employee;
import com.riifat.demo.repository.EmployeeRepository;
import com.riifat.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	   private EmployeeRepository employeeRepository;
	   
    @Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {

       
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
		
		Optional<Employee> employee= employeeRepository.findById(id);
		
		if(employee.isPresent()) {
			return employee.get();
		}
		else {
			throw new ResourceNotFoundException("Employee", "id", id);
		}
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		Employee existEmployee=employeeRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee", "Id", id));
		
		existEmployee.setFirstName(employee.getFirstName());
		existEmployee.setLastName(employee.getLastName());
		existEmployee.setEmail(employee.getEmail());
		employeeRepository.save(existEmployee);
		return existEmployee;
	}


	@Override
	public void deleteEmployee(long id) {
		
		//employee id exist or not -> check
		employeeRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee", "Id", id) );
		employeeRepository.deleteById(id);
	}

}
