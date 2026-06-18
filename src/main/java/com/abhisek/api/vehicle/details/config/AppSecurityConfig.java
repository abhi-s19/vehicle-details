package com.abhisek.api.vehicle.details.config;

import com.abhisek.api.vehicle.details.security.JwtSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtSecurityFilter jwtSecurityFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers(HttpMethod.GET,"/api/v1/vehicle-details").hasRole("USER").
                antMatchers(HttpMethod.POST,"/api/v1/vehicle-details").hasAnyRole("USER","ADMIN").
                anyRequest().permitAll().and().httpBasic().and().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class).
                csrf().disable();

	}
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
	
}
