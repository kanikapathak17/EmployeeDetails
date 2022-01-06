package com.project.demo.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Configurable;

import lombok.Value;

@Configurable
public class NameValidator implements ConstraintValidator<Name, String> {

  
   @Override
   public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
       if ((str != null)
               && (!str.equals(""))
               && (str.matches("^[a-zA-Z]*$"))) return true;
       return false;
   }


}
