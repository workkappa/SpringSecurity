package com.scsb.springsecurity01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    CustomUserDetailsManager customUserDetailsManager;

    @Bean
    AuthenticationManager authenticationManager(){
        KapthaAuthenticationProvider kapthaAuthenticationProvider = new KapthaAuthenticationProvider();
        kapthaAuthenticationProvider.setUserDetailsService(customUserDetailsManager);

        ProviderManager providerManager = new ProviderManager(kapthaAuthenticationProvider);
        return providerManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/logout", "/verify-code.jpg","/custom-error", "/css/**", "/js/**").permitAll()
                        // 所有請求開啟授權保護
                        .anyRequest()
                        // 已認證的請求會自動授權
                        .authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/login-success", true)
                        .failureUrl("/login-error").permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/login-error")
                );

//      http.csrf(csrf -> csrf.disable());
//      http.authenticationManager(authenticationManager());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
