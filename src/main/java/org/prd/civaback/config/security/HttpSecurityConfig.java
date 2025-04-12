package org.prd.civaback.config.security;

import org.prd.civaback.config.filter.JwtAuthenticationFilter;
import org.prd.civaback.util.EPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@Configuration
public class HttpSecurityConfig {

    private final AuthenticationProvider daoAuthProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public HttpSecurityConfig(AuthenticationProvider daoAuthProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.daoAuthProvider = daoAuthProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()).
                csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig->{
                    authConfig.requestMatchers("/bus").hasAuthority(EPermission.buses_view_all.name());
                    authConfig.requestMatchers("/bus/{id}").hasAuthority(EPermission.bus_view_detail.name());
                    authConfig.requestMatchers("/auth/**").permitAll();
                    authConfig.anyRequest().permitAll();
                });
        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource(
            @Value("${spring.web.cors.allowed-origins}") String allowedOrigins) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(allowedOrigins));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}