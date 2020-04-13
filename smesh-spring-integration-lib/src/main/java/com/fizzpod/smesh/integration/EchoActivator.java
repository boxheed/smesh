package com.fizzpod.smesh.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fizzpod.smesh.messaging.Parcel;


@Component
public class EchoActivator {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoActivator.class);

    public Parcel call(Message<Parcel> message) {
        LOGGER.info("Echo {}", message);
        return message.getPayload();
    }

}
