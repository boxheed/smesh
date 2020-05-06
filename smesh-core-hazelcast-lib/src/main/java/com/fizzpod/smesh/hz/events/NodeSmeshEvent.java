package com.fizzpod.smesh.hz.events;

import java.util.UUID;

import com.fizzpod.smesh.SmeshEventType;

public class NodeSmeshEvent extends AbstractSmeshEvent {

    private static final long serialVersionUID = 6235543431533223416L;

    public NodeSmeshEvent(UUID nodeId, SmeshEventType nodeState) {
        super(nodeId, nodeState);
    }

}
