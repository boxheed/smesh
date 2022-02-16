package com.fizzpod.smesh.planes.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "parameters" })
public class Parameters implements Serializable {


    private static final long serialVersionUID = 5052897044872094479L;

    @JsonProperty("parameters")
    private Map<String, Collection<String>> parameters = new HashMap<>();
    
    @JsonProperty("parameters")
    public Map<String, Collection<String>> getParameters() {
        return this.parameters;
    }
    
    @JsonProperty("parameters")
    public void setParameters(Map<String, Collection<String>> headers) {
        this.parameters = headers;
    }
    
    public void add(String key, String value) {
        Collection<String> values = this.parameters.get(key);
        if(values == null) {
            values = new LinkedList<>();
        }
        values.add(value);
        this.parameters.put(key, values);
    }
    
    public void addAll(String key, Collection<String> value) {
        Collection<String> values = this.parameters.get(key);
        if(values == null) {
            values = new LinkedList<>();
        }
        values.addAll(value);
        this.parameters.put(key, values);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
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
        Parameters other = (Parameters) obj;
        if (parameters == null) {
            if (other.parameters != null)
                return false;
        } else if (!parameters.equals(other.parameters))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Parameters [parameters=" + parameters + "]";
    }
    
    
    
}
