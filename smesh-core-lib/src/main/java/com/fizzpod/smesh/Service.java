package com.fizzpod.smesh;

import java.util.concurrent.Callable;

import com.fizzpod.smesh.messaging.Parcel;

public interface Service extends Callable<Parcel> {

    public String getName();
    
    public int getConcurrency();
    
    public boolean isAsync();
    
}
