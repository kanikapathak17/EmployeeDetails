package com.project.demo.controller;



import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.entity.Employee;
import com.project.demo.entity.SearchParameters;
import com.project.demo.repository.EmployeeRepository;
import com.project.demo.service.EmployeeService;









@RestController
@RequestMapping("/employee")
@Validated
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeservice;
	
	//private static final Logger logger = (Logger) LoggerFactory.getLogger(EmployeeController.class);
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EmployeeController.class);
	

	
	 
	
	
	
	
	@GetMapping(value="/")
	
	public List<Employee> getAllEmployee()
	{
		try {
			 log.info("kanika pathak");
			return employeeservice.getAllEmployee();
			
		}
		 catch(Exception e){
		// log.error("An exception occurred",e);
			//System.out.println("An error occurred" + e);
		 log.info("kanika pathak");
			 throw e;
			 
		 }
		
		 
		
	}
	
	@PostMapping(value="/")
	public String addEmployee( @RequestBody @Valid Employee employee)
	{
		try {
		 employeeservice.saveEmployee(employee);
		 log.info("kanika pathak");
		 System.out.println(employee);
		 return "added";
		}	
		catch(Exception e) {
			 System.out.println("An error occurred" + e);
			
			throw e;
		}
	}
	
	
	@DeleteMapping(value="/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id)
	{
		
		try {
		 employeeservice.deleteEmployee(id);
		
		 return "deleted row with id = "+ id;
		}
		catch(Exception e) {
			 System.out.println("An error occurred" + e);
			throw e;
		}
	}
	
	@PutMapping(value="/{id}")
	public Employee updateEmployee( @RequestBody @Valid Employee employee, @PathVariable("id") Integer id)
	{
		try {
		 employee= employeeservice.updateEmployee(employee, id);
		
		 return employee;
		}
		catch(Exception e) {
			System.out.println("An error occurred" + e);
			throw e;
		}
	}
	
	@GetMapping(value="/{id}")
	public Employee findEmployeeById(@PathVariable("id") Integer id)
	{
		try {
		 return employeeservice.findEmployeeById(id);
		
		}
		catch(Exception e) {
			 System.out.println("An error occurred" + e);
			throw e;
		}
		
	}
	
	@GetMapping(value="/list/{page}")
	public Page<Employee> getAllEmployee(@PathVariable("page") Integer page)
	{
		return employeeservice.getAllEmployee(page);
	}
	
	@PostMapping(value="/search")
	public List<Employee> search(@RequestBody SearchParameters search )
	{
		return employeeservice.search(search);
	}
	
	
	

}
