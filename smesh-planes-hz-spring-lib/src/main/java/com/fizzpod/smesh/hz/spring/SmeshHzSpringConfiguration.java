package com.fizzpod.smesh.hz.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fizzpod.smesh.hz.planes.control.HzControlPlane;
import com.fizzpod.smesh.hz.planes.data.HzDataPlane;
import com.fizzpod.smesh.planes.control.ControlPlane;
import com.fizzpod.smesh.planes.data.DataPlane;
import com.hazelcast.core.HazelcastInstance;

@Configuration(SmeshHzSpringConfiguration.BEAN_NAME)
public class SmeshHzSpringConfiguration {
    static final String BEAN_NAME = "SmeshHazelcastConfiguration";

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Bean
    public ControlPlane getControlPlane() {
        return new HzControlPlane(hazelcastInstance);
    }

    @Bean
    public DataPlane getDataPlane() {
        return new HzDataPlane(hazelcastInstance);
    }
    
}
