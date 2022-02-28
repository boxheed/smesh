package com.fizzpod.smesh.hz.planes.data;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.function.Function;

import com.fizzpod.smesh.hz.planes.control.HzControlPlane;
import com.fizzpod.smesh.planes.control.ServiceDefinition;
import com.fizzpod.smesh.planes.data.Parcel;
import com.fizzpod.smesh.planes.data.ParcelFilter;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

public class HzServiceCaller implements Callable<Parcel>, Serializable, HazelcastInstanceAware {

    private static final long serialVersionUID = -7226056657493242590L;
    
    private transient HazelcastInstance hazelcast;
    private transient HzControlPlane controlPlane;
    private transient ServiceDefinition definition;
    private transient Function<Parcel, Parcel> service;
    
    private Parcel parcel;

    public HzServiceCaller(Parcel parcel) {
        this.parcel = parcel;
    }
    
    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
        controlPlane = (HzControlPlane) hazelcast.getUserContext()
                .get(HzControlPlane.CONTROL_PLANE_CONTEXT_KEY);
        definition = controlPlane.getServiceDefinition();
        service = controlPlane.getService();
    }

    @Override
    public Parcel call() throws Exception {
        applyRequestFilters(parcel);
        Parcel result = callService();
        applyResponseFilters(result);
        return result;
    }

    private void applyRequestFilters(Parcel parcel) {
        ParcelFilter[] filters = definition.getRequestFilters();
        for(ParcelFilter filter: filters) {
            parcel = filter.apply(parcel);
        }
    }

    private void applyResponseFilters(Parcel result) {
        ParcelFilter[] filters = definition.getResponseFilters();
        for(ParcelFilter filter: filters) {
            result = filter.apply(result);
        }
    }

    private Parcel callService() {
        Parcel result = null;
        if(service != null) {
            result = service.apply(parcel);
        } else {
            throw new RuntimeException("Bummer");
        }
        return result;
    }

}
