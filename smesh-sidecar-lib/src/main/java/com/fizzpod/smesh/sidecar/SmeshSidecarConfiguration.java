package com.fizzpod.smesh.sidecar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fizzpod.smesh.gateway.SmeshGatewayConfiguration;
import com.fizzpod.smesh.planes.control.ControlPlane;

@Configuration(SmeshSidecarConfiguration.BEAN_NAME)
@Import({ SmeshGatewayConfiguration.class })
@ComponentScan(basePackageClasses = { SmeshGatewayConfiguration.class })
public class SmeshSidecarConfiguration {
    static final String BEAN_NAME = "smeshSidecarConfiguration";

    @Autowired
    ControlPlane controlPlane;

    @Bean
    public SidecarServiceFunction createService() {
//        throw new RuntimeException("banananna");
        SidecarServiceFunction service = new SidecarServiceFunction();
        controlPlane.registerService(service.getDefinition(), service);
        return service;
    }
    
}
