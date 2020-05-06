package com.fizzpod.smesh.hz.events;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fizzpod.smesh.SmeshEvent;
import com.fizzpod.smesh.SmeshEventType;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleEvent.LifecycleState;
import com.hazelcast.core.LifecycleListener;
import com.hazelcast.topic.ITopic;

/**
 * Listens to Hazelcast lifecycle events and adapts them to Smesh events
 * Hazelcast Lifecycle States Mapping: Fired when the member is starting.
 * STARTING: SmeshNodeState.STARTING Fired when the member start is completed.
 * STARTED: SmeshNodeState.STARTED Fired when the member is shutting down.
 * SHUTTING_DOWN: SmeshNodeState.SHUTTING_DOWN Fired when the member shut down
 * is completed. SHUTDOWN: SmeshNodeState.SHUTDOWN
 *
 * All other states are ignored
 *
 */
public class HzLifecycleListenerSmeshEventAdapter implements LifecycleListener, HazelcastInstanceAware {

    public static final String SMESH_EVENTS_TOPIC_NAME = "smesh-events";
    private static final Logger LOGGER = LoggerFactory.getLogger(HzLifecycleListenerSmeshEventAdapter.class);

    private HazelcastInstance hazelcast;

    @Override
    public void stateChanged(LifecycleEvent event) {
        LOGGER.info("Hazelcast LifecycleEvent {}, {}", event, hazelcast);
        SmeshEvent smeshEvent = this.createSmeshEvent(event);
        SmeshEventBus.post(smeshEvent);
    }

    private SmeshEvent createSmeshEvent(LifecycleEvent event) {
        SmeshEvent smeshEvent = null;
        LifecycleState state = event.getState();
        SmeshEventType smeshEventType = null;
        switch (state) {
        case STARTING:
            smeshEventType = SmeshEventType.CORE_STARTING;
            break;
        case STARTED:
            smeshEventType = SmeshEventType.CORE_STARTED;
            break;
        case SHUTTING_DOWN:
            smeshEventType = SmeshEventType.CORE_SHUTTING_DOWN;
            break;
        case SHUTDOWN:
            smeshEventType = SmeshEventType.CORE_SHUTDOWN;
            break;
        default:
            break;
        }
        if(smeshEventType != null) {
            UUID nodeId = this.hazelcast.getCluster().getLocalMember().getUuid();
            smeshEvent = new CoreSmeshEvent(nodeId, smeshEventType);
        }
        return smeshEvent;
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
    }

}
