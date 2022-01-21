package com.project.demo.entity;



import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter(value=AccessLevel.PUBLIC)
@Setter(value=AccessLevel.PUBLIC)
public class SearchParameters {
	
	private  String name;
	 private  String address;
	 @Pattern(regexp="^[1-9]\\d*(\\.\\d+)?$", message="Please Enter correctly")
	 private  String salary;
	private int Designation;
	
	
	
	 
	
	

}
