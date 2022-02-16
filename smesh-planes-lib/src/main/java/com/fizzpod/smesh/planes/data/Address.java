package com.fizzpod.smesh
.planes.data;

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
 * Address
 * <p>
 *  
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "method", "path"})
public class Address implements Serializable {

    private static final long serialVersionUID = 7380322958623380412L;

    /**
     * The destination
     * <p>
     * The address to send the payload to (required).
     * 
     */
    @JsonProperty("method")
    private String method;
    
    /**
     * The destination
     * <p>
     * The address to send the payload to (required).
     * 
     */
    @JsonProperty("path")
    private String path;
    
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }
    
    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.method = name;
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
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        result = prime * result + ((method == null) ? 0 : method.hashCode());
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
        if (path == null) {
            if (other.path != null)
                return false;
        } else if (!path.equals(other.path))
            return false;
        if (method == null) {
            if (other.method != null)
                return false;
        } else if (!method.equals(other.method))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Address [name=" + method + ", destination=" + path + ", additionalProperties="
                + additionalProperties + "]";
    }

}