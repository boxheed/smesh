package com.fizzpod.smesh.application.gateway;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.fizzpod.smesh.hz.planes.control.HzServiceDefinition;
import com.fizzpod.smesh.planes.control.ServiceDefinition;
import com.fizzpod.smesh.planes.data.Parcel;

public class TestService implements Function<Parcel, Parcel>{

    @Override
    public Parcel apply(Parcel request) {
        Parcel response = new Parcel();
        response.setStatus(201);
        response.setHeaders(request.getHeaders());
        Map<String, Object> body = new HashMap<>();
        body.put("name", "freddy");
        body.put("request", request);
        response.setBody(body);
        System.out.println("BANANA");
        return response;
    }
    
    public ServiceDefinition getDefinition() {
        Collection<String> routes = Arrays.asList(new String[] {"/testy.*"});
        HzServiceDefinition definition = new HzServiceDefinition(this.getClass().getName(), routes);
        return definition;
    }

}
