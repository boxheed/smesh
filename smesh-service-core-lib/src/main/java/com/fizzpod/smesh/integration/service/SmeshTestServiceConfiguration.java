package com.fizzpod.smesh.integration.service;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fizzpod.smesh.hz.spring.SmeshSpringConfiguration;
import com.fizzpod.smesh.integration.SmeshSpringIntegrationConfiguration;

@Configuration
@ImportResource({"classpath:META-INF/spring/smesh-test-service-context.xml"})
@Import({ SmeshSpringIntegrationConfiguration.class, SmeshSpringConfiguration.class })
@EnableIntegration
@EnableAsync
@Profile("smesh-service-test-echo")
public class SmeshTestServiceConfiguration {
    

}
