package com.fizzpod.smesh.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fizzpod.smesh.integration.http.SmeshHttpinterfaceConfiguration;
import com.fizzpod.smesh.integration.service.SmeshServiceConfiguration;
import com.fizzpod.smesh.services.experimental.SmeshExperimentalConfiguration;

@Configuration(SmeshApplicationConfiguration.BEAN_NAME)
@Import({ SmeshHttpinterfaceConfiguration.class, SmeshServiceConfiguration.class,
        SmeshExperimentalConfiguration.class })
public class SmeshApplicationConfiguration {
    static final String BEAN_NAME = "smeshApplicationConfiguration";
}
