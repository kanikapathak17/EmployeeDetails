package com.project.demo.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.demo.entity.Designation;
import com.project.demo.entity.Employee;
import com.project.demo.entity.SearchParameters;

@Repository
public class CriteriaApi {
	
	

	
private final EntityManager entity;

private final CriteriaBuilder criteriabuilder;


 public CriteriaApi(EntityManager entity) {
	super();
	this.entity = entity;
	
	 this.criteriabuilder=entity.getCriteriaBuilder();
	 
}
	 public List<Employee> getEmployee(SearchParameters search)
	 
	 
	 
	 {
        
      CriteriaBuilder builder = entity.getCriteriaBuilder();
        
      CriteriaQuery<Employee> employeeQuery = builder.createQuery(Employee.class);
        
      Root<Employee> employeeRoot = employeeQuery.from(Employee.class);
        
       Predicate finalPredicate=getpredicates(employeeRoot,search);
     
   employeeQuery.where(finalPredicate);
        
        TypedQuery<Employee> typedemployeeQuery = 
                            entity.createQuery(employeeQuery);
         List<Employee> list = typedemployeeQuery.getResultList(); 
        return list;
       
        }
	 
	 
	 

        
	private Predicate getpredicates(Root<Employee> employeeRoot, SearchParameters search) {
		 List<Predicate> predicate = new ArrayList<Predicate>();
		 
		 
	      if(search.getName()!=null){ 
	     predicate.add( criteriabuilder.like(employeeRoot.get("name"),"%"+ search.getName()+"%"));
	      }
	      
	      if(search.getAddress()!=null) {
	       predicate.add( criteriabuilder.like(employeeRoot.get("address"),"%"+search.getAddress()+"%"));
	      }
	      if(search.getSalary()!=null) { 
	        predicate.add(criteriabuilder.like(employeeRoot.get("salary"),"%"+ search.getSalary()+"%"));
	      }

	        return criteriabuilder.and(predicate.toArray(new Predicate[]{}));
	        
		
	}
	

}
