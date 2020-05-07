package com.fizzpod.smesh.hz.events;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fizzpod.smesh.SmeshEvent;
import com.fizzpod.smesh.SmeshServiceDefinition;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.map.MapEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryEvictedListener;
import com.hazelcast.map.listener.EntryLoadedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import com.hazelcast.map.listener.MapClearedListener;
import com.hazelcast.map.listener.MapEvictedListener;

public class HzSmeshRegistryEventListenerAdapter implements EntryAddedListener<String, SmeshServiceDefinition>,
        EntryRemovedListener<String, SmeshServiceDefinition>, EntryUpdatedListener<String, SmeshServiceDefinition>,
        EntryEvictedListener<String, SmeshServiceDefinition>, EntryLoadedListener<String, SmeshServiceDefinition>,
        MapEvictedListener, MapClearedListener, HazelcastInstanceAware {

    private HazelcastInstance hazelcast;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HzSmeshRegistryEventListenerAdapter.class);

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
    }

    @Override
    public void mapCleared(MapEvent event) {
        LOGGER.info("MapCleared {}", event);
        UUID nodeId = event.getMember().getUuid();
        SmeshEvent smeshEvent = ServiceRegistryEvent.createDeregisterEvent(nodeId);
        SmeshEventBus.post(smeshEvent);
    }

    @Override
    public void mapEvicted(MapEvent event) {
        LOGGER.info("MapEvicted {}", event);
        UUID nodeId = event.getMember().getUuid();
        SmeshEvent smeshEvent = ServiceRegistryEvent.createDeregisterEvent(nodeId);
        SmeshEventBus.post(smeshEvent);
    }

    @Override
    public void entryLoaded(EntryEvent<String, SmeshServiceDefinition> event) {
        LOGGER.info("EntryLoaded {}", event);
        UUID nodeId = event.getMember().getUuid();
        SmeshEvent smeshEvent = ServiceRegistryEvent.createRegisterEvent(nodeId);
        SmeshEventBus.post(smeshEvent);
    }

    @Override
    public void entryEvicted(EntryEvent<String, SmeshServiceDefinition> event) {
        LOGGER.info("EntryEvicted {}", event);
        UUID nodeId = event.getMember().getUuid();
        SmeshEvent smeshEvent = ServiceRegistryEvent.createDeregisterEvent(nodeId);
        SmeshEventBus.post(smeshEvent);
    }

    @Override
    public void entryUpdated(EntryEvent<String, SmeshServiceDefinition> event) {
        LOGGER.info("EntryUpdated {}", event);
        UUID nodeId = event.getMember().getUuid();      
        SmeshEvent smeshEvent = ServiceRegistryEvent.createRegisterEvent(nodeId);
        SmeshEventBus.post(smeshEvent);
    }

    @Override
    public void entryRemoved(EntryEvent<String, SmeshServiceDefinition> event) {
        LOGGER.info("EntryRemoved {}", event);
        UUID nodeId = event.getMember().getUuid();      
        SmeshEvent smeshEvent = ServiceRegistryEvent.createDeregisterEvent(nodeId);
        SmeshEventBus.post(smeshEvent);
    }

    @Override
    public void entryAdded(EntryEvent<String, SmeshServiceDefinition> event) {
        LOGGER.info("EntryAdded {}", event);
        UUID nodeId = event.getMember().getUuid();      
        SmeshEvent smeshEvent = ServiceRegistryEvent.createRegisterEvent(nodeId);
        SmeshEventBus.post(smeshEvent);
    }

}
