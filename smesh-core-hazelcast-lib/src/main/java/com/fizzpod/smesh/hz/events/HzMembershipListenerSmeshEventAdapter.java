package com.fizzpod.smesh.hz.events;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fizzpod.smesh.SmeshEvent;
import com.fizzpod.smesh.SmeshEventType;
import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

public class HzMembershipListenerSmeshEventAdapter implements MembershipListener, HazelcastInstanceAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(HzMembershipListenerSmeshEventAdapter.class);
    
    private HazelcastInstance hazelcast;
    
    @Override
    public void memberAdded(MembershipEvent event) {
        LOGGER.info("Smesh MemberAddedEvent {}, {}", event, hazelcast);
        UUID nodeId = event.getMember().getUuid();
        SmeshEvent smeshEvent = new NodeSmeshEvent(nodeId, SmeshEventType.NODE_ADDED);
        SmeshEventBus.post(smeshEvent);
    }

    @Override
    public void memberRemoved(MembershipEvent event) {
        LOGGER.info("Smesh MemberRemovedEvent {}, {}", event, hazelcast);
        UUID nodeId = event.getMember().getUuid();
        SmeshEvent smeshEvent = new NodeSmeshEvent(nodeId, SmeshEventType.NODE_ADDED);
        SmeshEventBus.post(smeshEvent);
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
    }

}
