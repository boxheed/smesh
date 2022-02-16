package com.fizzpod.smesh.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fizzpod.smesh.hz.spring.SmeshHzSpringConfiguration;

@Configuration(SmeshApplicationConfiguration.BEAN_NAME)
@Import({ SmeshHzSpringConfiguration.class })
public class SmeshApplicationConfiguration {
    static final String BEAN_NAME = "smeshApplicationConfiguration";
}
