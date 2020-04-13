package com.fizzpod.smesh.integration.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.integration.support.json.JsonObjectMapper;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fizzpod.smesh.Smesh;
import com.fizzpod.smesh.hz.spring.SmeshSpringConfiguration;
import com.fizzpod.smesh.integration.SmeshServiceActivator;

@Configuration
@ImportResource("classpath:META-INF/spring/smesh-http-client-context.xml")
@Import({ SmeshSpringConfiguration.class, SmeshIntegrationHttpWebSecurity.class })
@EnableIntegration
@EnableAsync
//@Profile("smesh-http-client")
public class SmeshHttpinterfaceConfiguration {
    
    @Autowired
    private Smesh smesh;
    
    @Bean(name = "jacksonJodaTimeObjectMapper")
    public JsonObjectMapper getJacksonJodaTimeObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonObjectMapper(mapper);
    }

    @Bean(name = "smeshServiceActivator")
    public SmeshServiceActivator getSmeshServiceActivator() {
        return new SmeshServiceActivator(smesh.getSmeshServiceRegister());
    }
    

}
