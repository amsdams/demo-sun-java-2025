package com.example.demosun2025.configuration;

import com.example.demosun2025.StringToIntervalConverter;
import com.example.demosun2025.StringToLocationConverter;
import com.example.demosun2025.TZIDToStringConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
 
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocationConverter());
        registry.addConverter(new TZIDToStringConverter());
        registry.addConverter(new StringToIntervalConverter());
    }
}
