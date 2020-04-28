package com.fizzpod.smesh.services.experimental.cmdline;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fizzpod.smesh.Smesh;
import com.fizzpod.smesh.SmeshService;
import com.fizzpod.smesh.SmeshServiceRegister;
import com.fizzpod.smesh.hz.spring.SmeshSpringConfiguration;

@Configuration
@Import({ SmeshSpringConfiguration.class })
@EnableAsync
@Profile("smesh-service-experimental-cmdline")
public class SmeshCmdlineServiceConfiguration {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SmeshCmdlineServiceConfiguration.class);
    
    @Autowired
    private Smesh smesh;
    
    public void registerCmdlineService() {
        String serviceName = "testcmdservice";
        LOGGER.info("Getting Service {}", serviceName);
        SmeshServiceRegister register = smesh.getSmeshServiceRegister();
        LOGGER.info("Registering service {}", serviceName);
        SmeshService service = register.registerService(serviceName);
//        return new SmeshServiceAdapter(service);
        
    }
    
}
