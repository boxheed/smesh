package com.fizzpod.smesh.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fizzpod.smesh.hz.spring.SmeshHzSpringConfiguration;
import com.fizzpod.smesh.planes.control.ControlPlane;

@Configuration(SmeshGatewayConfiguration.BEAN_NAME)
@Import({ SmeshHzSpringConfiguration.class })
public class SmeshGatewayConfiguration {
    static final String BEAN_NAME = "SmeshGatewayConfiguration";
    
    @Autowired
    ControlPlane controlPlane;
    
    @Bean
    public HttpRequestToParcelMapper getInboundMaper() {
        return new HttpRequestToParcelMapper();
    }

    @Bean
    public ParcelToHttpResponseMapper getOutboundMapper() {
        return new ParcelToHttpResponseMapper();
    }
    
//    @Bean 
//    public TestService createService() {
//        TestService service = new TestService();
//        controlPlane.registerService(service.getDefinition(), service);
//        return service;
//    }
    
}
