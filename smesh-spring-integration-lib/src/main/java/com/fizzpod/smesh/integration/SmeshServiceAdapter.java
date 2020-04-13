package com.fizzpod.smesh.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fizzpod.smesh.SmeshService;
import com.fizzpod.smesh.messaging.Parcel;

public class SmeshServiceAdapter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SmeshServiceAdapter.class);

    private SmeshService service;
    
    public SmeshServiceAdapter(SmeshService service) {
        this.service = service;
    }
    
    public Parcel take() {
        return service.receive();
    }
    
    public void respond(Parcel parcel) {
        service.respond(parcel);
    }
    
    
}
