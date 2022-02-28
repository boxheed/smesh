package com.fizzpod.smesh.hz.planes.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fizzpod.smesh.planes.data.ParcelFilter;
import com.fizzpod.smesh.planes.data.ServiceSelector;

public class HzServiceDefinitionBuilder {

    private String name;

    private List<ServiceSelector> selectors = new ArrayList<>();

    private List<ParcelFilter> requestFilters = new ArrayList<>();

    private List<ParcelFilter> responseFilters = new ArrayList<>();

    public HzServiceDefinitionBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    

    public HzServiceDefinitionBuilder selector(String selector) {
        return selector(this.parseSelector(selector));
    }
    
    public HzServiceDefinitionBuilder selectors(Collection<String> selectors) {
        for(String selector: selectors) {
            selector(selector);
        }
        return this;
    }

    public HzServiceDefinitionBuilder selector(ServiceSelector selector) {
        this.selectors.add(selector);
        return this;
    }

    private ServiceSelector parseSelector(String selector) {
        ServiceSelector newSelector;
        String[] parts = StringUtils.split(selector, ":", 2);
        switch (parts[0]) {
        case "json":
            newSelector = new JsonPathServiceSelector(parts[1]);
            break;
        case "method":
            newSelector = new MethodServiceSelector(parts[1]);
            break;
        case "header":
            newSelector = new HeaderServiceSelector(parts[1]);
            break;
        case "address":
            newSelector = new AddressServiceSelector(parts[1]);
            break;
        default:
            throw new RuntimeException(selector);
        }
        return newSelector;
    }

    public HzServiceDefinitionBuilder requestFilter(String filter) {
        return this.requestFilter(parseFilter(filter));
    }
    
    public HzServiceDefinitionBuilder requestFilters(Collection<String> filters) {
        for(String filter: filters) {
            this.requestFilter(filter);
        }
        return this;
    }

    public HzServiceDefinitionBuilder requestFilter(ParcelFilter filter) {
        this.requestFilters.add(filter);
        return this;
    }

    public HzServiceDefinitionBuilder responseFilter(String filter) {
        return this.responseFilter(parseFilter(filter));

    }
    
    public HzServiceDefinitionBuilder responseFilters(Collection<String> filters) {
        for(String filter: filters) {
            this.responseFilter(filter);
        }
        return this;
    }

    public HzServiceDefinitionBuilder responseFilter(ParcelFilter filter) {
        this.responseFilters.add(filter);
        return this;

    }

    private ParcelFilter parseFilter(String filter) {
        ParcelFilter newFilter;
        String[] parts = StringUtils.split(filter, ":", 2);
        switch (parts[0]) {
        case "header":
            newFilter = new HeaderParcelFilter(parts[1]);
            break;
        case "logger":
            newFilter = new LoggingParcelFilter(parts[1]);
            break;
        default:
            throw new RuntimeException(filter);
        }
        return newFilter;
    }
    
    public HzServiceDefinition build() {
        HzServiceDefinition serviceDefinition = new HzServiceDefinition();
        serviceDefinition.setName(name);
        serviceDefinition.setSelectors(selectors);
        serviceDefinition.setRequestFilters(requestFilters);
        serviceDefinition.setResponseFilters(responseFilters);
        return serviceDefinition;
    }
}
