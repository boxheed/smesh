package com.fizzpod.smesh.hz;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.fizzpod.smesh.SmeshService;
import com.fizzpod.smesh.SmeshServiceDefinition;
import com.fizzpod.smesh.SmeshServiceRegister;
import com.fizzpod.smesh.messaging.Address;
import com.hazelcast.cluster.Member;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HzSmeshServiceRegister implements SmeshServiceRegister {

    private static final String SMESH_SERVICE_REGISTER = "smesh-service-register";
    
    private static final String SMESH_QUEUE_PREFIX = "smesh-service-queue-";

    private HazelcastInstance hazelcast;
    
    private IMap<String, SmeshServiceDefinition> services;
    
    private HzResponseQueuePool pool;
    
    public HzSmeshServiceRegister(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
        this.services = this.hazelcast.getMap(SMESH_SERVICE_REGISTER);
        this.pool = new HzResponseQueuePool(hazelcast);
    }
    
    @Override
    public Collection<SmeshService> getServices() {
        Collection<SmeshServiceDefinition> definitions = services.values();
        List<SmeshService> services = new LinkedList<>();
        for(SmeshServiceDefinition definition: definitions) {
            HzSmeshService service = new HzSmeshService(hazelcast, definition, pool);
            services.add(service);
        }
        return Collections.unmodifiableCollection(services);
    }

    @Override
    public SmeshService getService(String serviceName) {
        return getHzSmeshService(serviceName);
    }
    
    private HzSmeshService getHzSmeshService(String serviceName) {
        if(services.containsKey(serviceName)) {
            SmeshServiceDefinition definition = services.get(serviceName);
            return new HzSmeshService(hazelcast, definition, pool);
        } else {
            //TODO update to more managed exceptions
            throw new RuntimeException("Service with name " + serviceName + " does not exist");
        }
    }

    @Override
    public boolean hasService(String serviceName) {
        return services.containsKey(serviceName);
    }

    @Override
    public SmeshServiceDefinition getServiceDefinition(String serviceName) {
        return services.get(serviceName);
    }

    @Override
    public SmeshService removeService(String serviceName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SmeshService registerService(String serviceName) {
        HzSmeshService service = null;
        if(!this.hasService(serviceName)) {
            Address serviceAddress = new Address();
            serviceAddress.setName(serviceName);
            serviceAddress.setDestination(SMESH_QUEUE_PREFIX + serviceName);
            HzSmeshServiceDefinition definition = new HzSmeshServiceDefinition(serviceAddress);
            this.services.put(definition.getName(), definition);
            service = new HzSmeshService(hazelcast, definition, pool);
        } else {
            service = this.getHzSmeshService(serviceName);
        }
        Member localMember = hazelcast.getCluster().getLocalMember();
        UUID localMemberId = localMember.getUuid();
        service.addServiceMember(localMemberId);
        return service;
    }

}
