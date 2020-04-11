package com.fizzpod.smesh.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fizzpod.smesh.integration.http.SmeshHttpinterfaceConfiguration;
import com.fizzpod.smesh.integration.service.SmeshFileServiceConfiguration;
import com.fizzpod.smesh.integration.service.SmeshTestServiceConfiguration;

@Configuration(SmeshApplicationConfiguration.BEAN_NAME)
@Import({ SmeshHttpinterfaceConfiguration.class, SmeshFileServiceConfiguration.class,
        SmeshTestServiceConfiguration.class })
public class SmeshApplicationConfiguration {
    static final String BEAN_NAME = "smeshApplicationConfiguration";
}
