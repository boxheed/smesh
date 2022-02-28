package com.fizzpod.smesh.hz.planes.data;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.Callable;

import com.fizzpod.smesh.hz.planes.control.HzControlPlane;
import com.fizzpod.smesh.planes.control.ServiceDefinition;
import com.fizzpod.smesh.planes.data.Parcel;
import com.fizzpod.smesh.planes.data.ServiceSelector;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

public class HzMemberSelector implements Callable<UUID>, Serializable, HazelcastInstanceAware {

    private static final long serialVersionUID = -8001988902266113636L;

    private transient HazelcastInstance hazelcast;

    private Parcel parcel;

    public HzMemberSelector(Parcel parcel) {
        this.parcel = parcel;
    }

    @Override
    public UUID call() throws Exception {
        HzControlPlane controlPlane = (HzControlPlane) hazelcast.getUserContext()
                .get(HzControlPlane.CONTROL_PLANE_CONTEXT_KEY);
        ServiceDefinition definition = controlPlane.getServiceDefinition();
        UUID uuid = null;
        if(definition != null) {
            ServiceSelector[] selectors = definition.getSelectors();
            boolean match = false;
            if (selectors.length > 0) {
                match = true;
                for (ServiceSelector selector : selectors) {
                    if (!selector.apply(parcel)) {
                        match = false;
                        break;
                    }
                }
            }
            uuid = match? this.hazelcast.getCluster().getLocalMember().getUuid(): null;
        }
        return uuid;
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcast = hazelcastInstance;
    }

}
