package com.fizzpod.smesh;

import com.fizzpod.smesh.messaging.Parcel;

public interface SmeshService {

    SmeshServiceDefinition getServiceDefinition();
    
    Parcel call(Parcel parcel);
    
    void respond(Parcel parcel);
    
    Parcel receive();
}
