package com.fizzpod.smesh;

import com.fizzpod.smesh.messaging.Parcel;

public interface SmeshServiceRequestListener {

    public void onCall(SmeshService smeshService, Parcel parcel);
    
}
