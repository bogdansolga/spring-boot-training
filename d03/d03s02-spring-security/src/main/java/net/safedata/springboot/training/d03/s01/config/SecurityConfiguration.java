package net.safedata.springboot.training.d03.s01.config;

import net.safedata.springboot.training.d03.s01.handler.FailedAuthHandler;
import net.safedata.springboot.training.d03.s01.handler.PostLogoutHandler;
import net.safedata.springboot.training.d03.s01.handler.SuccessfulAuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static net.safedata.springboot.training.d03.s01.controller.ProductController.API_PREFIX;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain webHttpSecurity(HttpSecurity http,
                                               SuccessfulAuthHandler successfulAuthHandler,
                                               FailedAuthHandler failedAuthHandler,
                                               PostLogoutHandler logoutHandler) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .formLogin(form -> configureFormHandlers(form, successfulAuthHandler, failedAuthHandler))
            .logout(logout -> logout.addLogoutHandler(logoutHandler))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST, API_PREFIX +"/auth/**").permitAll()
                    .requestMatchers(HttpMethod.POST, API_PREFIX +"/login", "/register").permitAll()
                    .anyRequest().authenticated())
            .httpBasic(withDefaults());
        return http.build();
    }

    private void configureFormHandlers(FormLoginConfigurer<HttpSecurity> form,
                                       SuccessfulAuthHandler successfulAuthHandler,
                                       FailedAuthHandler failedAuthHandler) {
        form.successHandler(successfulAuthHandler);
        form.failureHandler(failedAuthHandler);
        form.defaultSuccessUrl("/", true);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsManager userDetailsManager)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailsManager)
                   .passwordEncoder(passwordEncoder())
                   .and()
                   .build();
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

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                               .username("user")
                               .password("$2a$10$D5XLRx5LcWBmaMJhL76sLeVkqVHxtQAYdmOGjlhxptlC85KW6smOq") // password
                               .roles("USER")
                               .build();
        UserDetails admin = User.builder()
                                .username("admin")
                                .password("$2a$10$D5XLRx5LcWBmaMJhL76sLeVkqVHxtQAYdmOGjlhxptlC85KW6smOq") // password
                                .roles("USER", "ADMIN")
                                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
