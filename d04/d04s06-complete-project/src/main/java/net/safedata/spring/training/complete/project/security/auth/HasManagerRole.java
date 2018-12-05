package net.safedata.spring.training.complete.project.security.auth;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static net.safedata.spring.training.complete.project.security.auth.Roles.MANAGER_ROLE;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('" + MANAGER_ROLE + "')")
public @interface HasManagerRole {
}
