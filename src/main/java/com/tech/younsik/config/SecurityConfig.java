package com.tech.younsik.config;

import com.tech.younsik.config.jwt.JwtAuthEntryPoint;
import com.tech.younsik.config.jwt.JwtRequestFilter;
import com.tech.younsik.config.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_ACCESS_AVAIL = {
        "/health",
        "/auth-api/v1/login",
        "/user-api/v1/register"
    };

    private static final String[] ADMIN_ACCESS_AVAIL = {
        "/admin-api/*"
    };
    
    private static final String[] SWAGGER_ACCESS_AVAIL = {
        "/v2/api-docs",
        "/configuration/**",
        "/swagger*/**",
        "/webjars/**"
    };

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(jwtUserDetailsService)
            .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .headers().frameOptions().disable()
            .and()
            .formLogin().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(PUBLIC_ACCESS_AVAIL).permitAll()
            .antMatchers(SWAGGER_ACCESS_AVAIL).permitAll()
            .antMatchers(ADMIN_ACCESS_AVAIL).hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
