package com.training.OnlineTraining.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Collection;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {

		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsService());

		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorization -> authorization
				.anyRequest().permitAll());

		http.formLogin(form -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.usernameParameter("email")
				.permitAll()
				.successHandler(successHandler())
		);

		http.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {

		return (request, response, authentication) -> {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

			if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
				response.sendRedirect("/admin");
			} else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("CLIENT"))) {
				response.sendRedirect("/client");
			} else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("COACH"))) {
				response.sendRedirect("/coach");
			}
		};
	}


}