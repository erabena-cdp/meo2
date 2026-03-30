package com.hua.yes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Correct CORS configuration to allow your Vue app
                .cors(Customizer.withDefaults())

                // 2. Disable CSRF (required for POST/PUT requests from Vue)
                .csrf(csrf -> csrf.disable())

                // 3. Authorization Rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").permitAll()   // Your API
                        .requestMatchers("/h2-console/**").permitAll() // Access to Database UI
                        .anyRequest().authenticated()
                )

                // 4. THIS IS THE FIX: Allow H2 Console to show inside the browser frame
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}