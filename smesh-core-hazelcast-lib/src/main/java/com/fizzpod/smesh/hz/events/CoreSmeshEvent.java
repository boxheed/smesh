package com.fizzpod.smesh.hz.events;

import java.util.UUID;

import com.fizzpod.smesh.SmeshEventType;

public class CoreSmeshEvent extends AbstractSmeshEvent {

    private static final long serialVersionUID = -101671182741225650L;

    public CoreSmeshEvent(UUID nodeId, SmeshEventType nodeState) {
        super(nodeId, nodeState);
    }


}
