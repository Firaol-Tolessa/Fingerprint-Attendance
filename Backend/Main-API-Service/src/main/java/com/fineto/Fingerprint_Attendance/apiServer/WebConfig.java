package com.fineto.Fingerprint_Attendance.apiServer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        
        registry.addMapping("/api/**") // Only allow CORS for your API endpoints
                .allowedOrigins("http://localhost:5173") // Your React app's origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}