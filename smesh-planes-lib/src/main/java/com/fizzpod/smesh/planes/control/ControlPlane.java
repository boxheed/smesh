package com.fizzpod.smesh.planes.control;

import java.util.function.Function;

import com.fizzpod.smesh.planes.data.Parcel;

public interface ControlPlane {
    
    void registerService(ServiceDefinition definition, Function<Parcel, Parcel> service);
    
}
