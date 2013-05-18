package com.pne.arch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = "classpath:spring-security.xml")
public class SecurityConfig {}
