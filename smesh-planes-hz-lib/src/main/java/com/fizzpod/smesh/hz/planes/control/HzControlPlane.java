package com.fizzpod.smesh.hz.planes.control;

import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

import com.fizzpod.smesh.planes.control.ControlPlane;
import com.fizzpod.smesh.planes.control.Router;
import com.fizzpod.smesh.planes.control.ServiceDefinition;
import com.fizzpod.smesh.planes.data.Parcel;
import com.hazelcast.core.HazelcastInstance;

public class HzControlPlane implements ControlPlane {

    public static final String CONTROL_PLANE_CONTEXT_KEY = "control-plane";
    
    public static final String SERVICE_SET = "service-set";

    private HazelcastInstance hazelcast;

    private Function<Parcel, Parcel> service;
    
    private Set<HzServiceEntry> serviceRoutes;
    
    public HzControlPlane(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
        this.hazelcast.getUserContext().put(CONTROL_PLANE_CONTEXT_KEY, this);
        serviceRoutes = this.hazelcast.getSet(SERVICE_SET);
    }

    @Override
    public void registerService(ServiceDefinition definition, Function<Parcel, Parcel> service) {
        UUID memberId = this.hazelcast.getCluster().getLocalMember().getUuid();
        HzServiceEntry entry = new HzServiceEntry(definition, memberId);
        this.serviceRoutes.add(entry);
        this.service = service;
    }
    
    public Router getRouter() {
        return new HzRouter(hazelcast, serviceRoutes);
    }
    
    public Function<Parcel, Parcel> getService() {
        return service;
    }
    
}
