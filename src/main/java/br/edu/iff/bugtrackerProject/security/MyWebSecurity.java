package br.edu.iff.bugtrackerProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService service;
   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").hasRole("ADMIN")
                .antMatchers("/usuarios/meusdados/**").hasAnyRole("ADMIN", "FUNC")
                .antMatchers("/usuarios").hasRole("ADMIN")
                .antMatchers("/usuarios/**").hasAnyRole("ADMIN", "FUNC")
                .antMatchers("/projetos").hasAnyRole("ADMIN", "FUNC")
                .antMatchers("/projetos/**").hasAnyRole("ADMIN", "FUNC")
                .antMatchers("/**").hasAnyRole("ADMIN", "FUNC")                
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .usernameParameter("email");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
}
