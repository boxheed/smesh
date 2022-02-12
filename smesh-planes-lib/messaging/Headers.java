package com.fizzpod.smesh.messaging;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "headers" })
public class Headers implements Serializable {

    private static final long serialVersionUID = 4983376862475925020L;

    @JsonProperty("headers")
    private Map<String, String> headers = new HashMap<>();
    
    @JsonProperty("headers")
    public Map<String, String> getHeaders() {
        return this.headers;
    }
    
    @JsonProperty("headers")
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    
    public void add(String key, String value) {
        this.headers.put(key, value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((headers == null) ? 0 : headers.hashCode());
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
        Headers other = (Headers) obj;
        if (headers == null) {
            if (other.headers != null)
                return false;
        } else if (!headers.equals(other.headers))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Headers [headers=" + headers + "]";
    }
    
    
    
}
