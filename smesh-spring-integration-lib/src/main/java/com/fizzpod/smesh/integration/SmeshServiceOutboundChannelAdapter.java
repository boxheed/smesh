package com.fizzpod.smesh.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fizzpod.smesh.messaging.Parcel;

@Component
public class SmeshServiceOutboundChannelAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SmeshServiceOutboundChannelAdapter.class);
    
    public void handle(Message<Parcel> message) {
        logger.info("Handling it {}", message);
    }
    
}
