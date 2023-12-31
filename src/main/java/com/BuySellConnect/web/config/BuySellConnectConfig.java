package com.BuySellConnect.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.BuySellConnect.web.dao.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class BuySellConnectConfig extends WebSecurityConfigurerAdapter  {
	
	@Bean
	public UserDetailsService getUserDetailService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	// configure method 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/BuySellConnect/admin/**")
		.hasRole("ADMIN")
		.antMatchers("/BuySellConnect/user/**")
		.hasRole("USER")
		.antMatchers("/BuySellConnect/**","/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/productimages/**")
		.permitAll().and()
		.formLogin()
		.loginPage("/BuySellConnect/login")
		.defaultSuccessUrl("/BuySellConnect/user/products")
		.and()
		.csrf()
		.disable();
		
	}
	
	/*@Override
    public void configure(WebSecurity web) throws Exception {
        web
        .ignoring()
        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }*/
	
}
	
