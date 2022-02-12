package com.fizzpod.smesh.planes.data;

import java.io.Serializable;
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
@JsonPropertyOrder({ "id", "recipient", "sender", "headers", "payload" })
public class Parcel implements Serializable {

    private static final long serialVersionUID = -4296043408980819286L;

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
    @JsonProperty("recipient")
    private Address recipient = new Address();

    /**
     * ID Information
     * <p>
     * Unique ID for the parcel (Required)
     * 
     */
    @JsonProperty("sender")
    private Address sender = new Address();
    
    @JsonProperty("headers")
    private Headers headers = new Headers();
    
    /**
     * Payload
     * <p>
     * The payload of the message. (Required)
     * 
     */
    @JsonProperty("payload")
    private Object payload;

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
    
    @JsonProperty("sender")
    public Address getSender() {
        return sender;
    }

    @JsonProperty("sender")
    public void setSender(Address sender) {
        this.sender = sender;
    }

    @JsonProperty("recipient")
    public Address getRecipient() {
        return recipient;
    }

    @JsonProperty("recipient")
    public void setRecipient(Address recipient) {
        this.recipient = recipient;
    }

    @JsonProperty("headers")
    public Headers getHeaders() {
        return headers;
    }

    @JsonProperty("headers")
    public void setHeaders(Headers headers) {
        this.headers = headers;
    }
    
    /**
     * Payload
     * <p>
     * The payload of the message. (Required)
     * 
     * @return The payload
     */
    @JsonProperty("payload")
    public Object getPayload() {
        return payload;
    }

    /**
     * Payload
     * <p>
     * The payload of the message. (Required)
     * 
     * @param payload The payload
     */
    @JsonProperty("payload")
    public void setPayload(Object payload) {
        this.payload = payload;
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
        result = prime * result + ((headers == null) ? 0 : headers.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        result = prime * result + ((meta == null) ? 0 : meta.hashCode());
        result = prime * result + ((payload == null) ? 0 : payload.hashCode());
        result = prime * result + ((recipient == null) ? 0 : recipient.hashCode());
        result = prime * result + ((sender == null) ? 0 : sender.hashCode());
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
        if (payload == null) {
            if (other.payload != null)
                return false;
        } else if (!payload.equals(other.payload))
            return false;
        if (recipient == null) {
            if (other.recipient != null)
                return false;
        } else if (!recipient.equals(other.recipient))
            return false;
        if (sender == null) {
            if (other.sender != null)
                return false;
        } else if (!sender.equals(other.sender))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Parcel [id=" + id + ", recipient=" + recipient + ", sender=" + sender + ", headers="
                + headers + ", payload=" + payload + ", additionalProperties=" + additionalProperties + "]";
    }

}
