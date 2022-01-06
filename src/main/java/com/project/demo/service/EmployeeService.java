package com.project.demo.service;



import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.demo.entity.Employee;
import com.project.demo.entity.SearchParameters;
import com.project.demo.repository.CriteriaApi;
import com.project.demo.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;


@Service
public class EmployeeService 
{
	@Autowired
	EmployeeRepository repo;
	
	@Autowired
	CriteriaApi api;
	
	
	
	
	
	
	public void saveEmployee( Employee employee)
	{
	
	
		
		try {
		repo.save(employee);
		}
		catch(Exception e) {
			System.out.println("An error occurred" + e);
			throw e;
		}
		
	}
	
	public List<Employee> getAllEmployee()
	{
		try {
		
		 return (List<Employee>)repo.findAll();
		}	 
		catch(Exception e) {
			System.out.println("An error occurred" + e);
			throw e;
		}
	}
	

	public void deleteEmployee(Integer id) {
		try {
		 repo.deleteById(id);
		}
		catch(Exception e) {
			System.out.println("An error occurred" + e);
			throw e;
		}
	}

	public Employee updateEmployee(Employee employee, Integer id) {
		try {
		employee.setId(id);
		repo.save(employee);
		return employee;
		}
		catch(Exception e) {
			System.out.println("An error occurred" + e);
			throw e;
		}
	}

	public Employee findEmployeeById(Integer id) {
		
		try {
		Employee employee=repo.findById(id).orElseThrow();
		return employee;
	}
		catch(Exception e) {
			System.out.println("An error occurred" + e);
			throw e;
		}
	}
	
	
	public Page<Employee> getAllEmployee(Integer page){
		
		System.out.println("working");
		
		Pageable pageable = PageRequest.of(page, 4);
		return repo.findAll(pageable);
	}

	public List<Employee> search(SearchParameters search) {
		
		return api.getEmployee(search);
	}
	
	
}
