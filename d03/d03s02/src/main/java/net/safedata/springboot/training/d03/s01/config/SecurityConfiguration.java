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
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
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
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
@SuppressWarnings("unused")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] IGNORED_ENDPOINTS = {"/info", "/about"};

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder())
            .withUser("user")
            // the unencrypted password is 'password'
            .password("$2a$10$4xnpk2a5jLr1mf6VWle6Vuv4q7DBsW2rqQcg6N1Ms/y4g98Ry4D4C")
            .roles(Roles.MANAGER_ROLE);

        /*
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("SELECT user FROM Users user WHERE user.userName = ? AND user.active = 'true'")
            .authoritiesByUsernameQuery("SELECT auth FROM Authorities auth WHERE auth.userName = ?");
        */
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/static/**").permitAll()
            .antMatchers(HttpMethod.POST, "/admin")
                .hasAnyRole(Roles.ADMIN_ROLE, Roles.MANAGER_ROLE)
            .antMatchers(HttpMethod.GET, "/product").fullyAuthenticated()
            .antMatchers(HttpMethod.POST, "/product").hasAuthority("WRITE")
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
        
        http.csrf().disable();

        final RememberMeConfigurer<HttpSecurity> rememberMeConfigurer = http.rememberMe();
        rememberMeConfigurer.key("x");

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
    public void configure(final WebSecurity web) {
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
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken("john", "doe",
                Collections.singleton(new SimpleGrantedAuthority(Roles.ADMIN_ROLE)));
        authentication.setAuthenticated(true);

        Map<String, Integer> map = new HashMap<>();
        map.put("userId", 25);
        authentication.setDetails(map);

        securityContext.setAuthentication(authentication);
    }

    private void obtainAuthContext() {
        // obtaining the security context
        SecurityContext existingContext = SecurityContextHolder.getContext();
        final Authentication authentication = existingContext.getAuthentication();
        authentication.getDetails();
    }
}
