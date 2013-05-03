package com.pne.arch.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = { "com.pne.arch.domain" })
@Import({ PersistenceConfig.class, SecurityConfig.class })
public class RootConfig {}