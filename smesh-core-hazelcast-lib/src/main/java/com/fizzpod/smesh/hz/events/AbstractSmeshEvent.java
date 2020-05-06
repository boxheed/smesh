package com.fizzpod.smesh.hz.events;

import java.io.Serializable;
import java.util.UUID;

import com.fizzpod.smesh.SmeshEvent;
import com.fizzpod.smesh.SmeshEventType;

public abstract class AbstractSmeshEvent implements SmeshEvent, Serializable {

    private static final long serialVersionUID = -1608455414749031436L;

    private UUID nodeId;
    
    private SmeshEventType nodeState;

    public AbstractSmeshEvent(UUID nodeId, SmeshEventType nodeState) {
        this.nodeId = nodeId;
        this.nodeState = nodeState;
    }

    @Override
    public SmeshEventType getSmeshNodeState() {
        return nodeState;
    }

    @Override
    public UUID getNodeId() {
        return nodeId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
        result = prime * result + ((nodeState == null) ? 0 : nodeState.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractSmeshEvent other = (AbstractSmeshEvent) obj;
        if (nodeId == null) {
            if (other.nodeId != null)
                return false;
        } else if (!nodeId.equals(other.nodeId))
            return false;
        if (nodeState != other.nodeState)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "HzSmeshEvent [nodeId=" + nodeId + ", nodeState=" + nodeState + "]";
    }
    
    

}
