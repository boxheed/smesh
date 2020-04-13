package com.fizzpod.smesh.hz.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fizzpod.smesh.Smesh;
import com.fizzpod.smesh.hz.HzSmesh;
import com.fizzpod.smesh.hz.HzSmeshServiceRegister;
import com.hazelcast.core.HazelcastInstance;

@Configuration(SmeshSpringConfiguration.BEAN_NAME)
public class SmeshSpringConfiguration {
    static final String BEAN_NAME = "SmeshHazelcastConfiguration";
    
    @Autowired
    private HazelcastInstance hazelcastInstance;
    
    @Bean
    public Smesh getSmesh() {
        HzSmeshServiceRegister register = getSmeshRegister();
        return new HzSmesh(hazelcastInstance, register);
    }
    
    @Bean
    public HzSmeshServiceRegister getSmeshRegister() {
        return new HzSmeshServiceRegister(hazelcastInstance);
    }
}
