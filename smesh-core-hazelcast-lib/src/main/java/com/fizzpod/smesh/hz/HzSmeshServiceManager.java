package com.fizzpod.smesh.hz;

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

/**
 * Listens to events from 
 * @author andy
 *
 */
public class HzSmeshServiceManager
        implements EntryAddedListener<String, SmeshServiceDefinition>, EntryRemovedListener<String, SmeshServiceDefinition>,
        EntryUpdatedListener<String, SmeshServiceDefinition>, EntryEvictedListener<String, SmeshServiceDefinition>, EntryLoadedListener<String, SmeshServiceDefinition>,
        MapEvictedListener, MapClearedListener, HazelcastInstanceAware {

    private HazelcastInstance hazelcast;   
    
    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
    }

    @Override
    public void mapCleared(MapEvent event) {
        
    }

    @Override
    public void mapEvicted(MapEvent event) {
        
    }

    @Override
    public void entryLoaded(EntryEvent<String, SmeshServiceDefinition> event) {
        
    }

    @Override
    public void entryEvicted(EntryEvent<String, SmeshServiceDefinition> event) {
        
    }

    @Override
    public void entryUpdated(EntryEvent<String, SmeshServiceDefinition> event) {
        
    }

    @Override
    public void entryRemoved(EntryEvent<String, SmeshServiceDefinition> event) {
        
    }

    @Override
    public void entryAdded(EntryEvent<String, SmeshServiceDefinition> event) {
        
    }

}
