package com.example.hshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public WebSecurityConfig(
            TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/h2-console/**", "/favicon.ico");   //h2-console로 접속시 springSecurity가 접근을 막으므로 해당 코드 필요.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .cors().disable()
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않음

                .and()
                .authorizeRequests()
                // swagger 401 issue
                .antMatchers("/").anonymous()
                .antMatchers("/swagger-ui.html").anonymous()
                .antMatchers("/webjars/**").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/v2/**").anonymous()
                .antMatchers("/csrf").anonymous()

                // before login 401 issue
                .antMatchers("/api/v1/account/login").permitAll()   // login
                .antMatchers("/api/v1/account").permitAll() // sign
                .antMatchers("/api/v1/account/email/**").permitAll()    // send email
                .antMatchers("/api/v1/account/password/find").permitAll()   // pw find
                .antMatchers("/api/v1/account/password/change").permitAll() // pw chg

                // auth
                .antMatchers("/api/v1/authenticate").permitAll() // pw chg

                // mail test
                .antMatchers("/api/v1/mail/send").permitAll()

                // others
                .anyRequest().permitAll()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}
