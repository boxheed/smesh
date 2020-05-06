package com.fizzpod.smesh.hz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fizzpod.smesh.SmeshEvent;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;

public class HzSmeshEventListener implements MessageListener<SmeshEvent>, HazelcastInstanceAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(HzSmeshEventListener.class);

    private HazelcastInstance hazelcast;
    
    @Override
    public void onMessage(Message<SmeshEvent> message) {
        LOGGER.info("Received SmeshEvent {}", message);
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
    }

}
