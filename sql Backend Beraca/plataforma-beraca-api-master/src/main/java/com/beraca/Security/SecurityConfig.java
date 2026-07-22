package com.beraca.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Deshabilitar CSRF (común y necesario para APIs REST stateless)
            .csrf(csrf -> csrf.disable())

            // 2. Habilitar la configuración de CORS que definimos abajo
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // 3. Configurar permisos de rutas
            .authorizeHttpRequests(auth -> auth
                // Permitir peticiones públicas a los endpoints de registro/autenticación
                .requestMatchers("/api/**", "/usuarios/**", "/login/**").permitAll()
                // Si quieres permitir TODO temporalmente para desarrollo, usa:
                // .anyRequest().permitAll()
                .anyRequest().permitAll() 
            );

        return http.build();
    }

    // Configuración explícita de CORS para permitir tu frontend en Vite (localhost:5173)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permitir peticiones desde tu Frontend en desarrollo
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "https://escuela-beraca-1.onrender.com"));
        
        // Permitir todos los métodos HTTP
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Permitir todos los encabezados (headers)
        configuration.setAllowedHeaders(List.of("*"));
        
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
