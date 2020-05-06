package com.fizzpod.smesh.hz.events;

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

public class HzSmeshRegistryEventListenerAdapter
        implements EntryAddedListener<String, String>, EntryRemovedListener<String, String>,
        EntryUpdatedListener<String, String>, EntryEvictedListener<String, String>, EntryLoadedListener<String, String>,
        MapEvictedListener, MapClearedListener, HazelcastInstanceAware {

    private HazelcastInstance hazelcast;

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
    }

    @Override
    public void mapCleared(MapEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mapEvicted(MapEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void entryLoaded(EntryEvent<String, String> event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void entryEvicted(EntryEvent<String, String> event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void entryUpdated(EntryEvent<String, String> event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void entryRemoved(EntryEvent<String, String> event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void entryAdded(EntryEvent<String, String> event) {
        // TODO Auto-generated method stub

    }

}
