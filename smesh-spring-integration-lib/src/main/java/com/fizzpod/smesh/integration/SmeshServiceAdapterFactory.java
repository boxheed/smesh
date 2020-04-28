package com.fizzpod.smesh.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fizzpod.smesh.Smesh;
import com.fizzpod.smesh.SmeshService;
import com.fizzpod.smesh.SmeshServiceRegister;

@Component
public class SmeshServiceAdapterFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmeshServiceAdapterFactory.class);
    
    private Smesh smesh;
    
    public SmeshServiceAdapterFactory(Smesh smesh) {
        this.smesh = smesh;
    }
    
    public SmeshServiceAdapter getAdapter(String serviceName) {
        LOGGER.info("Getting Service {}", serviceName);
        SmeshServiceRegister register = smesh.getSmeshServiceRegister();
        if(!register.hasService(serviceName)) {
            LOGGER.info("Registering service {}", serviceName);
            register.registerService(serviceName);
        }
        SmeshService service = smesh.getSmeshService(serviceName);
        return new SmeshServiceAdapter(service);
    }
    
}
