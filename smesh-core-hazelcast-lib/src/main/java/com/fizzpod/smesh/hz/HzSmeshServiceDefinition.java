package com.fizzpod.smesh.hz;

import java.io.Serializable;

import com.fizzpod.smesh.SmeshServiceDefinition;
import com.fizzpod.smesh.messaging.Address;

public class HzSmeshServiceDefinition implements SmeshServiceDefinition, Serializable {

    private static final long serialVersionUID = 5974995138486641333L;

    private Address serviceAddress;

    public HzSmeshServiceDefinition(Address serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    @Override
    public String getName() {
        return this.serviceAddress.getName();
    }

    @Override
    public Address getAddress() {
        return this.serviceAddress;
    }

}
