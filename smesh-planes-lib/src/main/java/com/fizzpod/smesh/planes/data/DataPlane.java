package com.fizzpod.smesh.planes.data;

import java.util.concurrent.ExecutionException;

public interface DataPlane {

    Parcel call(Parcel parcel) throws InterruptedException, ExecutionException;
    
}
