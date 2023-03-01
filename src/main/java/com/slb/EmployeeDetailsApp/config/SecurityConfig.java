package com.slb.EmployeeDetailsApp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
	   http.csrf().disable()
	   .authorizeHttpRequests()
	   .requestMatchers(
               "/v2/api-docs", 
               "/swagger-resources/**",  
               "/swagger-ui.html", 
               "/webjars/**" ,
                /*Probably not needed*/ "/swagger.json")
	   .permitAll()
       .anyRequest()
       .authenticated()
	   .and().formLogin();
	   
	  return http.build();
	  }
	
}
