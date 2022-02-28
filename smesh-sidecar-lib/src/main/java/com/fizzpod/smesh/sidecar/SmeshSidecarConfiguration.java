package com.fizzpod.smesh.sidecar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fizzpod.smesh.gateway.SmeshGatewayConfiguration;
import com.fizzpod.smesh.hz.planes.control.HzServiceDefinition;
import com.fizzpod.smesh.hz.planes.control.HzServiceDefinitionBuilder;
import com.fizzpod.smesh.planes.control.ControlPlane;

@Configuration(SmeshSidecarConfiguration.BEAN_NAME)
@Import({ SmeshGatewayConfiguration.class })
@ComponentScan(basePackageClasses = { SmeshGatewayConfiguration.class })
@EnableConfigurationProperties(SmeshServiceConfigurationProperties.class)
public class SmeshSidecarConfiguration {
    static final String BEAN_NAME = "smeshSidecarConfiguration";

    @Autowired
    private ControlPlane controlPlane;

    @Autowired
    private SmeshServiceConfigurationProperties props;
    
    @Bean
    public SidecarServiceFunction createService() {
        SidecarServiceFunction service = new SidecarServiceFunction(props.getUrl());
        HzServiceDefinition definition = new HzServiceDefinitionBuilder().name(props.getName())
                .selectors(props.getSelectors()).requestFilters(props.getRequestFilters())
                .responseFilters(props.getResponseFilters()).build();
        controlPlane.registerService(definition, service);
        return service;
    }

}
