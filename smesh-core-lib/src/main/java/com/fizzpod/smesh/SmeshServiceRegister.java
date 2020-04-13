package com.fizzpod.smesh;

import java.util.Collection;

public interface SmeshServiceRegister {

    Collection<SmeshService> getServices();

    SmeshService getService(String serviceName);

    boolean hasService(String serviceName);

    SmeshServiceDefinition getServiceDefinition(String serviceName);

    SmeshService removeService(String serviceName);

    SmeshService registerService(String serviceName);
}
