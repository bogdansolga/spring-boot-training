package net.safedata.springboot.training.d03.s01.config;

import net.safedata.springboot.training.d03.s01.filter.SampleFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            //.passwordEncoder(passwordEncoder())
            .withUser("user")
            .password("password")
            .roles("USER");
    }

    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/static/**", "/about").permitAll()
                .antMatchers(HttpMethod.POST, "/admin").hasAnyRole("ADMIN", "MANAGER")
                .anyRequest().authenticated()
                .and()
        .formLogin();

        // setAuthenticated();
        // setAuthenticationDetails();
        // obtainAuthContext();

        // adding a new filter
        http.addFilterAfter(new SampleFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    private void setAuthenticated() {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        authentication.setAuthenticated(true);
    }

    private void setAuthenticationDetails() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("john", "doe",
                Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
        authentication.setAuthenticated(true);

        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        authentication.setDetails(map);

        securityContext.setAuthentication(authentication);
    }

    private void obtainAuthContext() {
        // obtaining the security context
        SecurityContext existingContext = SecurityContextHolder.getContext();
        existingContext.getAuthentication();
    }
}
