package com.fizzpod.smesh.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fizzpod.smesh.SmeshService;
import com.fizzpod.smesh.SmeshServiceRegister;
import com.fizzpod.smesh.messaging.Address;
import com.fizzpod.smesh.messaging.Parcel;


public class SmeshServiceActivator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmeshServiceActivator.class);
    
    private SmeshServiceRegister register;
    
    public SmeshServiceActivator(SmeshServiceRegister register) {
        this.register = register;
    }
    
    public Parcel callService(Message<Parcel> message) {
        String serviceName = getServiceName(message);
        SmeshService client = register.getService(serviceName);
        
        LOGGER.info("Calling service {}", serviceName);

        Parcel response = client.call(message.getPayload());
        LOGGER.info("Response: {}", response);
        return response;
    }

    private String getServiceName(Message<Parcel> message) {
        Parcel parcel = message.getPayload();
        Address recipient = parcel.getRecipient();
        return recipient.getName();
    }
    
}
