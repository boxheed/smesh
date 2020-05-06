package com.fizzpod.smesh.hz.events;

import com.fizzpod.smesh.SmeshEvent;
import com.google.common.eventbus.EventBus;

public class SmeshEventBus {

    private static final EventBus eventBus = new EventBus("smesh-events");
    
    public static void post(SmeshEvent smeshEvent) {
        eventBus.post(smeshEvent);
    }
    
}
