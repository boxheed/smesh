package com.fizzpod.smesh.application.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SmeshGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmeshGateway.class);

    /**
     * Instantiates a new Smesh application.
     */
    protected SmeshGateway() {
    }

    /**
     * Main entry point of the SMESH application.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SmeshGateway.class)
                .logStartupInfo(true).banner(new SmeshApplicationBanner()).run(args);
    }

}
