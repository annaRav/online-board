package com.api.onlineboard.config;

import com.api.onlineboard.security.jwt.JwtConfigurer;
import com.api.onlineboard.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final static String AUTH_ENDPOINT = "/api/v1/auth/**";
    private final static String OFFER_ENDPOINT = "/offers/**";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //todo end points to constant
        http
            .httpBasic()
            .disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests()
            .antMatchers(AUTH_ENDPOINT, OFFER_ENDPOINT)
            .permitAll()
            .antMatchers(HttpMethod.DELETE,"/users/{id}", "/products/{id}").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and().apply(new JwtConfigurer(jwtTokenProvider))
            .and()
            .headers().frameOptions().disable();
    }
}
