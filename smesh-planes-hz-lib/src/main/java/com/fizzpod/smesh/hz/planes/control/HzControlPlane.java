package com.fizzpod.smesh.hz.planes.control;

import java.util.UUID;
import java.util.function.Function;

import com.fizzpod.smesh.planes.control.ControlPlane;
import com.fizzpod.smesh.planes.control.ServiceDefinition;
import com.fizzpod.smesh.planes.data.Parcel;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.multimap.MultiMap;

public class HzControlPlane implements ControlPlane {

    public static final String CONTROL_PLANE_CONTEXT_KEY = "control-plane";
    
    public static final String SERVICE_MAP = "smesh-service-map";

    private HazelcastInstance hazelcast;

    private Function<Parcel, Parcel> service;
    
    private boolean healthyService = false;
    
    private ServiceDefinition definition;
    
    private MultiMap<ServiceDefinition, UUID> services;
    
    public HzControlPlane(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
        this.hazelcast.getUserContext().put(CONTROL_PLANE_CONTEXT_KEY, this);
        services = this.hazelcast.getMultiMap(SERVICE_MAP);
    }

    @Override
    public void registerService(ServiceDefinition definition, Function<Parcel, Parcel> service) {
        UUID memberId = this.hazelcast.getCluster().getLocalMember().getUuid();
        this.services.put(definition, memberId);
        this.definition = definition;
        this.service = service;
    }
    
    public ServiceDefinition getServiceDefinition() {
        return this.definition;
    }
    
    public Function<Parcel, Parcel> getService() {
        return service;
    }
    
}
