package com.javasampleapproach.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(bycryptEncoder())
				.usersByUsernameQuery("select username,passwd_hash,enabled from users where username=?")
				.authoritiesByUsernameQuery("SELECT  users.username,roles.alias " +
						" FROM" +
						"  users" +
						"  INNER JOIN t_users_roles ON users.user_id = t_users_roles.user_id AND users.username=?" +
						"  INNER JOIN roles ON t_users_roles.role_id= roles.user_role_id");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home","/register","/adduser")
                .permitAll().antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll()
                .and().logout().logoutSuccessUrl("/")
                .permitAll();
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder bycryptEncoder() {
        return new BCryptPasswordEncoder();
    }



}