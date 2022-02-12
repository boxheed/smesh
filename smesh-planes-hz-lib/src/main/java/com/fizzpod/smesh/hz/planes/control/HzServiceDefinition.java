package com.fizzpod.smesh.hz.planes.control;

import java.util.ArrayList;
import java.util.Collection;

import com.fizzpod.smesh.planes.control.ServiceDefinition;

public class HzServiceDefinition implements ServiceDefinition {

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
        return null;
    }

}
