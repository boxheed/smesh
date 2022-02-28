package com.fizzpod.smesh.sidecar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.fizzpod.smesh.common.spring.SmeshApplicationBanner;


@SpringBootApplication
public class SmeshSidecar {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmeshSidecar.class);

    /**
     * Instantiates a new Smesh application.
     */
    protected SmeshSidecar() {
    }

    /**
     * Main entry point of the SMESH application.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SmeshSidecar.class)
                .logStartupInfo(true).banner(new SmeshApplicationBanner()).run(args);
    }

}
