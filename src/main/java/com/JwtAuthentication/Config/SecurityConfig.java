package com.JwtAuthentication.Config;

import com.JwtAuthentication.security.JwtAuthenticationEntryPoint;
import com.JwtAuthentication.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


		http.csrf(csrf -> csrf.disable())
				.cors().and()
				.authorizeHttpRequests(auth->auth
						.requestMatchers("home/**")
						.authenticated()
						.requestMatchers("/auth/login").permitAll().
						requestMatchers("/auth/createUser")
						.permitAll().anyRequest().authenticated())
				.exceptionHandling(exp->exp.authenticationEntryPoint(point))
				.sessionManagement(se->se.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

//	@Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider(){
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//		return daoAuthenticationProvider;
//	}


}
