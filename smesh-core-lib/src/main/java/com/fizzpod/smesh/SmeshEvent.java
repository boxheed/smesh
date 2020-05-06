package com.fizzpod.smesh;

import java.util.UUID;

public interface SmeshEvent {

    public SmeshEventType getSmeshNodeState();
    
    public UUID getNodeId();
    
}
