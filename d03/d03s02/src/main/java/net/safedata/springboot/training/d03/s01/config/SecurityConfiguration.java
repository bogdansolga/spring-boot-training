package net.safedata.springboot.training.d03.s01.config;

import net.safedata.springboot.training.d03.s01.filter.SampleFilter;
import net.safedata.springboot.training.d03.s01.handler.FailedAuthHandler;
import net.safedata.springboot.training.d03.s01.handler.PostLogoutHandler;
import net.safedata.springboot.training.d03.s01.handler.SuccessfulAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] IGNORED_ENDPOINTS = {"/health", "/about"};

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            //.passwordEncoder(passwordEncoder())
            .withUser("user")
            .password("password")
            .roles("USER");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resources/static/**", "/about").permitAll()
            .antMatchers(HttpMethod.POST, "/admin").hasAnyRole("ADMIN", "MANAGER")
            .antMatchers(HttpMethod.GET, "/product").fullyAuthenticated()
            .anyRequest().authenticated();

        // registering the post auth handlers
        // they are registered as beans in order to be able to inject other dependencies in them (if needed)
        http.formLogin()
            .successHandler(successfulAuthHandler())
            .failureHandler(failedAuthHandler())
            .defaultSuccessUrl("/")
            .failureUrl("/login?error")
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll();

        // registering the post logout handler
        http.logout()
            .deleteCookies("JSESSIONID")
            .clearAuthentication(true)
            .addLogoutHandler(postLogoutHandler());

        configureSessionManagement(http);

        // setAuthenticated();
        // setAuthenticationDetails();
        // obtainAuthContext();

        // adding a new filter
        http.addFilterAfter(new SampleFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers(IGNORED_ENDPOINTS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SuccessfulAuthHandler successfulAuthHandler() {
        return new SuccessfulAuthHandler();
    }

    @Bean
    public FailedAuthHandler failedAuthHandler() {
        return new FailedAuthHandler();
    }

    @Bean
    public PostLogoutHandler postLogoutHandler() {
        return new PostLogoutHandler();
    }

    private void configureSessionManagement(HttpSecurity http) throws Exception {
        final SessionManagementConfigurer<HttpSecurity> sessionManagement = http.sessionManagement();
        sessionManagement.maximumSessions(3);
        sessionManagement.invalidSessionStrategy(new SimpleRedirectInvalidSessionStrategy("/login"));
        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
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
        map.put("userId", "25");
        authentication.setDetails(map);

        securityContext.setAuthentication(authentication);
    }

    private void obtainAuthContext() {
        // obtaining the security context
        SecurityContext existingContext = SecurityContextHolder.getContext();
        existingContext.getAuthentication();
    }
}
