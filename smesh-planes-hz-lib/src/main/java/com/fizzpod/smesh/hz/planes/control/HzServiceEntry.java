package com.fizzpod.smesh.hz.planes.control;

import java.io.Serializable;
import java.util.UUID;

import com.fizzpod.smesh.planes.control.ServiceDefinition;

public class HzServiceEntry implements Serializable {

    private static final long serialVersionUID = 40397144374784772L;

    private ServiceDefinition definition;
    
    private UUID memberId;

    public HzServiceEntry() {
        
    }
    
    public HzServiceEntry(ServiceDefinition definition, UUID memberId) {
        this.definition = definition;
        this.memberId = memberId;
    }

    public ServiceDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(ServiceDefinition definition) {
        this.definition = definition;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((definition == null) ? 0 : definition.hashCode());
        result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
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
        HzServiceEntry other = (HzServiceEntry) obj;
        if (definition == null) {
            if (other.definition != null)
                return false;
        } else if (!definition.equals(other.definition))
            return false;
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        } else if (!memberId.equals(other.memberId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "HzServiceEntry [definition=" + definition + ", memberId=" + memberId + "]";
    }
    
}
