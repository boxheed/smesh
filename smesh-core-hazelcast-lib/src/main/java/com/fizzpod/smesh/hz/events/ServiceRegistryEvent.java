package com.fizzpod.smesh.hz.events;

import java.util.UUID;

import com.fizzpod.smesh.SmeshEventType;

public class ServiceRegistryEvent extends AbstractSmeshEvent {

    private static final long serialVersionUID = 5310046480877272377L;

    private ServiceRegistryEvent(UUID nodeId, SmeshEventType nodeState) {
        super(nodeId, nodeState);
    }

    public static ServiceRegistryEvent createRegisterEvent(UUID nodeId) {
        return new ServiceRegistryEvent(nodeId, SmeshEventType.SERVICE_REGISTERED);
    }

    public static ServiceRegistryEvent createDeregisterEvent(UUID nodeId) {
        return new ServiceRegistryEvent(nodeId, SmeshEventType.SERVICE_DEREGISTERED);
    }
    
}
