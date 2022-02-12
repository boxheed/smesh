package com.fizzpod.smesh.messaging;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Span
 * <p>
 * A span contains the tracking information for a request moving through the
 * system.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "name", "destination"})
public class Address implements Serializable {

    private static final long serialVersionUID = 7380322958623380412L;

    /**
     * The destination
     * <p>
     * The address to send the payload to (required).
     * 
     */
    @JsonProperty("name")
    private String name;
    
    /**
     * The destination
     * <p>
     * The address to send the payload to (required).
     * 
     */
    @JsonProperty("destination")
    private String destination;
    
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("destination")
    public String getDestination() {
        return destination;
    }

    @JsonProperty("destination")
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * The Trace ID
     * <p>
     * The trace id for the span.
     * 
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Address other = (Address) obj;
        if (additionalProperties == null) {
            if (other.additionalProperties != null)
                return false;
        } else if (!additionalProperties.equals(other.additionalProperties))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Address [name=" + name + ", destination=" + destination + ", additionalProperties="
                + additionalProperties + "]";
    }

}
