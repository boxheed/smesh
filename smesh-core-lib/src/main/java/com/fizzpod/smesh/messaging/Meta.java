package com.fizzpod.smesh.messaging;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Meta Information
 * <p>
 * Container for meta data information about
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "trace" })
public class Meta implements Serializable {

    private static final long serialVersionUID = 3686621992693948292L;

    /**
     * Packet Trace Information
     * <p>
     * Container in the meta section containing trace information for the payload.
     * 
     */
    @JsonProperty("trace")
    private Trace trace;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Packet Trace Information
     * <p>
     * Container in the meta section containing trace information for the payload.
     * 
     * @return The trace
     */
    @JsonProperty("trace")
    public Trace getTrace() {
        return trace;
    }

    /**
     * Packet Trace Information
     * <p>
     * Container in the meta section containing trace information for the payload.
     * 
     * @param trace The trace
     */
    @JsonProperty("trace")
    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
        result = prime * result + ((trace == null) ? 0 : trace.hashCode());
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
        Meta other = (Meta) obj;
        if (additionalProperties == null) {
            if (other.additionalProperties != null)
                return false;
        } else if (!additionalProperties.equals(other.additionalProperties))
            return false;
        if (trace == null) {
            if (other.trace != null)
                return false;
        } else if (!trace.equals(other.trace))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Meta [trace=" + trace + ", additionalProperties=" + additionalProperties + "]";
    }

}
