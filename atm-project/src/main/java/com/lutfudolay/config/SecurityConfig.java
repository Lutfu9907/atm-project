package com.lutfudolay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/users/register").permitAll()       // Register serbest
//                .requestMatchers(HttpMethod.GET, "/api/users/**").permitAll() // Tüm GET /api/users istekleri serbest
//                .anyRequest().authenticated() // Diğer her şey auth ister
//            )
//            .httpBasic(Customizer.withDefaults()); // Basic auth açık
        
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll() // tüm endpointleri serbest bırak
        );
        
        return http.build();
    }
}

