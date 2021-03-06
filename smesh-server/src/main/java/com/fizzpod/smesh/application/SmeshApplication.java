package com.fizzpod.smesh.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SmeshApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmeshApplication.class);

    /**
     * Instantiates a new Smesh application.
     */
    protected SmeshApplication() {
    }

    /**
     * Main entry point of the SMESH application.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SmeshApplication.class)
                .logStartupInfo(true).banner(new SmeshApplicationBanner()).run(args);
    }

}
