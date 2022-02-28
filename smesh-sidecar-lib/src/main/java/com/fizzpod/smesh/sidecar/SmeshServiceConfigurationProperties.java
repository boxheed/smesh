package com.fizzpod.smesh.sidecar;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

//TODO this needs to move into a better library
@ConfigurationProperties(prefix = "smesh.service")
public class SmeshServiceConfigurationProperties {
    
    private String name;
    
    private String url;
    
    private String healthUrl;
    
    private List<String> selectors;
    
    private List<String> requestFilters;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getHealthUrl() {
        return url;
    }

    public void setHealthUrl(String url) {
        this.url = url;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSelectors() {
        return selectors;
    }

    public void setSelectors(List<String> selectors) {
        this.selectors = selectors;
    }

    public List<String> getRequestFilters() {
        return requestFilters;
    }

    public void setRequestFilters(List<String> requestFilters) {
        this.requestFilters = requestFilters;
    }

    public List<String> getResponseFilters() {
        return responseFilters;
    }

    public void setResponseFilters(List<String> responseFilters) {
        this.responseFilters = responseFilters;
    }

    private List<String> responseFilters;
    
    
}
