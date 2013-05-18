package com.pne.arch.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.pne.arch.config", "com.pne.arch.service", "com.pne.arch.repository" })
@Import({ PersistenceConfig.class, SecurityConfig.class, MailConfig.class })
public class RootConfig {}
