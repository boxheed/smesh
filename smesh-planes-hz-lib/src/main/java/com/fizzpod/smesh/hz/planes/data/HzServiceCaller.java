package com.fizzpod.smesh.hz.planes.data;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.function.Function;

import com.fizzpod.smesh.hz.planes.control.HzControlPlane;
import com.fizzpod.smesh.planes.data.Parcel;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

public class HzServiceCaller implements Callable<Parcel>, Serializable, HazelcastInstanceAware {

    private static final long serialVersionUID = -7226056657493242590L;
    
    private transient HazelcastInstance hazelcast;
    
    private Parcel parcel;

    public HzServiceCaller(Parcel parcel) {
        this.parcel = parcel;
    }
    
    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
    }

    @Override
    public Parcel call() throws Exception {
        @SuppressWarnings("unchecked")
        HzControlPlane controlPlane = (HzControlPlane) hazelcast.getUserContext()
                .get(HzControlPlane.CONTROL_PLANE_CONTEXT_KEY);
        Function<Parcel, Parcel> service = controlPlane.getService();
        Parcel result = null;
        if(service != null) {
            result = service.apply(parcel);
        } else {
            throw new RuntimeException("Bummer");
        }
        return result;
    }

}
