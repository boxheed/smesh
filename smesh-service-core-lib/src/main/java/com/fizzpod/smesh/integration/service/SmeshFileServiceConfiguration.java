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
@ImportResource({"${smesh.services.filecontextpath}"})
@Import({ SmeshSpringIntegrationConfiguration.class, SmeshSpringConfiguration.class })
@EnableIntegration
@EnableAsync
@Profile("smesh-service-file-context")
public class SmeshFileServiceConfiguration {
    

}
