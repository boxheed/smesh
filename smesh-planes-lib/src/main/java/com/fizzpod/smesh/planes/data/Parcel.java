package com.fizzpod.smesh.planes.data;

import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Message Packet
 * <p>
 * Message packet containing the payload (the actual message) and meta
 * information about the request.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "method", "status", "path", "parameters", "headers", "body" })
public class Parcel implements Serializable {

    private static final long serialVersionUID = -4296043408980819286L;

    public enum Method {GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE};
    
    /**
     * ID Information
     * <p>
     * Unique ID for the parcel (Required)
     * 
     */
    @JsonProperty("id")
    private String id = UUID.randomUUID().toString();
    
    /**
     * ID Information
     * <p>
     * Unique ID for the parcel (Required)
     * 
     */
    @JsonProperty("path")
    private String path;

    @JsonProperty("headers")
    private Headers headers = new Headers();
    
    @JsonProperty("parameters")
    private Parameters parameters = new Parameters();
    
    @JsonProperty("method")
    private Method method;
    
    @JsonProperty("status")
    private int status = 503;

    /**
     * Payload
     * <p>
     * The payload of the message. (Required)
     * 
     */
    @JsonProperty("body")
    private Object body;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }
    
    @JsonProperty("path")
    public String getAddress() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("headers")
    public Headers getHeaders() {
        return headers;
    }

    @JsonProperty("headers")
    public void setHeaders(Headers headers) {
        this.headers = headers;
    }
    
    @JsonProperty("parameters")
    public Parameters getParameters() {
        return parameters;
    }

    @JsonProperty("parameters")
    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
    
    @JsonProperty("method")
    public Method getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(Method method) {
        this.method = method;
    }
    
    @JsonProperty("status")
    public int getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(int status) {
        this.status = status;
    }
    
    /**
     * Payload
     * <p>
     * The payload of the message. (Required)
     * 
     * @return The payload
     */
    @JsonProperty("body")
    public Object getBody() {
        return body;
    }

    /**
     * Payload
     * <p>
     * The payload of the message. (Required)
     * 
     * @param body The body content
     */
    @JsonProperty("body")
    public void setBody(Object body) {
        this.body = body;
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
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + ((headers == null) ? 0 : headers.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((method == null) ? 0 : method.hashCode());
        result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        result = prime * result + status;
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
        Parcel other = (Parcel) obj;
        if (additionalProperties == null) {
            if (other.additionalProperties != null)
                return false;
        } else if (!additionalProperties.equals(other.additionalProperties))
            return false;
        if (body == null) {
            if (other.body != null)
                return false;
        } else if (!body.equals(other.body))
            return false;
        if (headers == null) {
            if (other.headers != null)
                return false;
        } else if (!headers.equals(other.headers))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (method != other.method)
            return false;
        if (parameters == null) {
            if (other.parameters != null)
                return false;
        } else if (!parameters.equals(other.parameters))
            return false;
        if (path == null) {
            if (other.path != null)
                return false;
        } else if (!path.equals(other.path))
            return false;
        if (status != other.status)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Parcel [id=" + id + ", path=" + path + ", headers=" + headers + ", parameters=" + parameters
                + ", method=" + method + ", status=" + status + ", body=" + body + ", additionalProperties="
                + additionalProperties + "]";
    }

}
