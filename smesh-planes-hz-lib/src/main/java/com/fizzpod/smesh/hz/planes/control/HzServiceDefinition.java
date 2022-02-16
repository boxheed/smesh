package com.fizzpod.smesh.hz.planes.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.fizzpod.smesh.planes.control.ServiceDefinition;

public class HzServiceDefinition implements ServiceDefinition, Serializable {

    private static final long serialVersionUID = -3894325186282124626L;

    private String name;
    
    private Collection<String> routes = new ArrayList<>(); 
    
    public HzServiceDefinition(String name) {
        this.name = name;
    }
    
    public HzServiceDefinition(String name, Collection<String> routes) {
        this.name = name;
        this.routes.addAll(routes);
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<String> getRoutes() {
        return Collections.unmodifiableCollection(routes);
    }
    
}
