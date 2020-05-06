package com.fizzpod.smesh.hz;

import com.fizzpod.smesh.Smesh;
import com.fizzpod.smesh.SmeshService;
import com.fizzpod.smesh.SmeshServiceRegister;
import com.hazelcast.core.HazelcastInstance;

public class HzSmesh implements Smesh {
    
    private HzSmeshServiceRegister register;
    private HazelcastInstance hazelcast;

    public HzSmesh(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
        this.register = new HzSmeshServiceRegister(hazelcast);
    }

    @Override
    public SmeshServiceRegister getSmeshServiceRegister() {
        return register;
    }

    @Override
    public SmeshService getSmeshService(String name) {
        SmeshService service = register.getService(name);
        return service;
    }

}
