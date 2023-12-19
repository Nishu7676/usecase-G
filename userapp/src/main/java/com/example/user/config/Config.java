package com.example.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.user.filter.JWTFilter;
import com.example.user.service.UserInfoDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Config {
	@Autowired
	private JWTFilter authFilter;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {      return http.csrf().disable().cors().and()
       		.authorizeHttpRequests()
               .requestMatchers("/auth/v1.0/user/**").permitAll()
               .requestMatchers("/auth/v1.0/user/*").permitAll()
               .and()
               .authorizeHttpRequests().requestMatchers("/userMarketdb/**")
               .authenticated()
               .and()
               .authorizeHttpRequests().requestMatchers("/v1.0/market/company/*")
               .authenticated()
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
		/*
		 * return http.csrf().disable().cors().and().authorizeRequests().antMatchers(
		 * "/auth/v1.0/user/**").permitAll()
		 * .antMatchers("/api/v1.0/**").authenticated().and().sessionManagement()
		 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		 * .authenticationProvider(authenticationProvider())
		 * .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		 */
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}