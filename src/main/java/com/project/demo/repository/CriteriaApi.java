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
	//cb= entity.getCriteriaBuilder();
     
	
	
    public List<Employee> getEmployee(SearchParameters search) {
        
        /** CriteriaBuilder instance**/ 
        CriteriaBuilder builder = entity.getCriteriaBuilder();
        
        /** create a criteriaQuery Object **/
        CriteriaQuery<Employee> employeeQuery = builder.createQuery(Employee.class);
        
        /** create a Root Object -> references to the queried entity **/
        Root<Employee> employeeRoot = employeeQuery.from(Employee.class);
        
       // Join<Employee, Designation> join = employeeRoot.join("designation");
        
       // Predicate designation =  criteriabuilder.equal(join.get("id"), search.getDesignation());
        /** Path Object to refer the attribute name of entity **/
        /** we are not using it for now **/
        //Path<Object> namePath = employeeRoot.get("name");
//        List<Predicate> predicate = new ArrayList<>();
//      if(search.getName()!=null)  
//     predicate.add( criteriabuilder.equal(employeeRoot.get("name"), search.getName()));
//      if(search.getAddress()!=null) 
//       predicate.add( criteriabuilder.equal(employeeRoot.get("address"),search.getAddress()));
//      if(search.getSalary()!=null) 
//        predicate.add(criteriabuilder.equal(employeeRoot.get("salary"), search.getSalary()));
//       // Predicate  test = criteriabuilder.or(address,salary);
      //  Predicate finalp = criteriabuilder.or(test, name);
        //Predicate finalPredicate= criteriabuilder.and(predicate.toArray(new Predicate[0]));
        Predicate finalPredicate=getpredicates(employeeRoot,search);
     
        
    
     
        /** Using explicit joins **/
        
     /*  CriteriaBuilder criteriabuilder = entity.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = criteriabuilder.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        Join<Employee, Designation> join = root.join("designation");
         join.on(criteriabuilder.equal(root.get("id"), join.get("employee")));
        cq.multiselect(join);
        List<Employee> resultList = entity.createQuery(cq).getResultList();*/
        

        
        /** Aggregate Expression for count operation  **/
        //Expression<Long> countExpression = builder.count(studentRoot);
        
        /** **/
      //  employeeQuery.select(employeeRoot).where(finalPredicate);
        employeeQuery.where(finalPredicate);
        
        /** instance of Typed Query */
        TypedQuery<Employee> typedemployeeQuery = 
                            entity.createQuery(employeeQuery);
        
        /** return the result **/
        List<Employee> list = typedemployeeQuery.getResultList(); 
        
       return list;
       
        
    }
	private Predicate getpredicates(Root<Employee> employeeRoot, SearchParameters search) {
		 List<Predicate> predicate = new ArrayList<Predicate>();
	      if(search.getName()!=null){ 
	     predicate.add( criteriabuilder.equal(employeeRoot.get("name"), search.getName()));
	      }
	      
	      if(search.getAddress()!=null) {
	       predicate.add( criteriabuilder.equal(employeeRoot.get("address"),search.getAddress()));
	      }
	      if(search.getSalary()!=null) { 
	        predicate.add(criteriabuilder.equal(employeeRoot.get("salary"), search.getSalary()));
	      }
//	       // Predicate  test = criteriabuilder.or(address,salary);
	      //  Predicate finalp = criteriabuilder.or(test, name);
	        return criteriabuilder.and(predicate.toArray(new Predicate[]{}));
	        
		
	}
	

}
