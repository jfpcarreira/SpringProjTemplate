package com.pne.arch.config;

import org.springframework.context.annotation.*;

@Configuration
@ImportResource(value = "classpath:spring-security-context.xml")
public class SecurityConfig {}