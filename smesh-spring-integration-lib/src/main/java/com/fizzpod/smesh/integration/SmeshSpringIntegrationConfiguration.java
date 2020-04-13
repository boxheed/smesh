package com.fizzpod.smesh.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fizzpod.smesh.Smesh;

@Configuration
public class SmeshSpringIntegrationConfiguration {

    @Autowired
    private Smesh smesh;
    
    @Bean(name="smeshServiceAdapterFactory")
    public SmeshServiceAdapterFactory getSmeshServiceAdapterFactory() {
        return new SmeshServiceAdapterFactory(smesh);
    }
    
    @Bean(name="echoActivator")
    public EchoActivator getEchoActivator() {
        return new EchoActivator();
    }
}
