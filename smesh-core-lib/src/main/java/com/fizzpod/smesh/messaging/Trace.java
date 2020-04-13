package com.fizzpod.smesh.messaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Packet Trace Information
 * <p>
 * Container in the meta section containing trace information for the payload.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "id", "spans" })
public class Trace implements Serializable {

    private static final long serialVersionUID = 3384683505535407185L;

    /**
     * The Trace ID
     * <p>
     * The current trace id if there is one.
     * 
     */
    @JsonProperty("id")
    private String id = "";
    /**
     * Spans
     * <p>
     * Container of the spans conveying trace information.
     * 
     */
    @JsonProperty("spans")
    private List<Span> spans = new ArrayList<Span>();

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * The Trace ID
     * <p>
     * The current trace id if there is one.
     * 
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * The Trace ID
     * <p>
     * The current trace id if there is one.
     * 
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Spans
     * <p>
     * Container of the spans conveying trace information.
     * 
     * @return The spans
     */
    @JsonProperty("spans")
    public List<Span> getSpans() {
        return spans;
    }

    /**
     * Spans
     * <p>
     * Container of the spans conveying trace information.
     * 
     * @param spans The spans
     */
    @JsonProperty("spans")
    public void setSpans(List<Span> spans) {
        this.spans = spans;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((spans == null) ? 0 : spans.hashCode());
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
        Trace other = (Trace) obj;
        if (additionalProperties == null) {
            if (other.additionalProperties != null)
                return false;
        } else if (!additionalProperties.equals(other.additionalProperties))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (spans == null) {
            if (other.spans != null)
                return false;
        } else if (!spans.equals(other.spans))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Trace [id=" + id + ", spans=" + spans + ", additionalProperties=" + additionalProperties + "]";
    }

}
