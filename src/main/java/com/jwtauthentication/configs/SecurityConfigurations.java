package com.jwtauthentication.configs;

import com.jwtauthentication.security.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.jwtauthentication.security.utils.UserPermission.*;
import static com.jwtauthentication.security.utils.UserRole.ADMIN;
import static com.jwtauthentication.security.utils.UserRole.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {
    private static final String[] publicUrls = {
            "/api/user/login",
            "/api/user/register"
    };

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(publicUrls).permitAll()
                           .requestMatchers("/api/auth/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                           .requestMatchers(GET, "/api/auth/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                           .requestMatchers(POST, "/api/auth/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                           .requestMatchers(PUT, "/api/auth/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                           .requestMatchers(DELETE, "/api/auth/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                           .anyRequest()
                           .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
