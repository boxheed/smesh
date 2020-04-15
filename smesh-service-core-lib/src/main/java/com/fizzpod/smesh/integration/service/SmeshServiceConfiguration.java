package com.fizzpod.smesh.integration.service;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.EnableIntegration;

import com.fizzpod.smesh.hz.spring.SmeshSpringConfiguration;

@Configuration
@ComponentScan(basePackageClasses = SmeshServiceConfiguration.class)
@EnableIntegration
public class SmeshServiceConfiguration {
    

}
