package com.example.springsecurityhashingandsalting14102022.security.annotation.employees;


import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.springsecurityhashingandsalting14102022.security.SecurityRoles.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Secured(ROLES_PREFIX + EMPLOYEES_ADMIN)
public @interface IsEmployeesAdmin {
}
