package com.project.demo.service;



import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import com.project.demo.controller.EmployeeController;
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
	
	
	
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	
	
	
	
	
	public void saveEmployee( Employee employee)
	{
	
	
		
		try {
		repo.save(employee);
		}
		catch(Exception e) {
			log.error("An error occurred" + e);
			throw e;
		}
		
	}
	
	public List<Employee> getAllEmployee()
	{
		try {
		
		 return (List<Employee>)repo.findAll();
		}	 
		catch(Exception e) {
			log.error("An error occurred" + e);
			throw e;
		}
	}
	
   @CacheEvict(value="employee",key="#id")
	public void deleteEmployee(Integer id) {
		try {
		 repo.deleteById(id);
		}
		catch(Exception e) {
			log.error("An error occurred" + e); 
			throw e;
		}
	}
	
	
	@CachePut(value="employee",key="#employee.id")
   public Employee updateEmployee(Employee employee, Integer id) {
		try {
		employee.setId(id);
		repo.save(employee);
		return employee;
		}
		catch(Exception e) {
			log.error("An error occurred" + e);
			throw e;
		}
	}
	
	

	
	public Page<Employee> getAllEmployee(Integer page,Integer Items){
		
		log.info("working");
		
		Pageable pageable = PageRequest.of(page, Items);
		return repo.findAll(pageable);
	}
	
	
	public List<Employee> search(SearchParameters search) {
		
		return api.getEmployee(search);
	}

	

	@Cacheable(value="employee", key="#id")
	public Employee findEmployeeById( Integer id) {
	
   log.info("fetching data from db");
		Employee employee= repo.findById(id).orElseThrow();

	
	return employee;
		
	}
	
	
   

	
}
