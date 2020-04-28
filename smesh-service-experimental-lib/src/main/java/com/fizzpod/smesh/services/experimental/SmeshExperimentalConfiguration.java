package com.fizzpod.smesh.services.experimental;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fizzpod.smesh.services.experimental.cmdline.SmeshCmdlineServiceConfiguration;

@Configuration
@Import({ SmeshCmdlineServiceConfiguration.class })
public class SmeshExperimentalConfiguration {
    

}
