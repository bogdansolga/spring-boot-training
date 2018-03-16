package net.safedata.springboot.training.d03.s06.security.auth;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static net.safedata.springboot.training.d03.s06.security.auth.Roles.MANAGER_ROLE;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('" + MANAGER_ROLE + "')")
public @interface HasManagerRole {
}
