package com.fizzpod.smesh.hz.planes.control;

import java.util.function.Function;

import com.fizzpod.smesh.planes.control.ControlPlane;
import com.fizzpod.smesh.planes.control.Router;
import com.fizzpod.smesh.planes.control.ServiceDefinition;
import com.fizzpod.smesh.planes.data.Parcel;
import com.hazelcast.core.HazelcastInstance;

public class HzControlPlane implements ControlPlane {

    public static final String CONTROL_PLANE_CONTEXT_KEY = "control-plane";

    private HazelcastInstance hazelcast;

    private Function<Parcel, Parcel> service;
    
    public HzControlPlane(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
        this.hazelcast.getUserContext().put(CONTROL_PLANE_CONTEXT_KEY, this);
    }

    @Override
    public void registerService(ServiceDefinition definition, Function<Parcel, Parcel> service) {
        this.service = service;
    }
    
    public Router getRouter() {
        return null;
    }
    
    public Function<Parcel, Parcel> getService() {
        return service;
    }
    
}
