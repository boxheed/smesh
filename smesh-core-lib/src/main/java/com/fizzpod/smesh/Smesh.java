package com.fizzpod.smesh;

public interface Smesh {

    SmeshServiceRegister getSmeshServiceRegister();
    
    SmeshService getSmeshService(String name);
    
}
