package com.fizzpod.smesh.hz.planes.control;

import java.io.Serializable;
import java.util.function.Function;

import com.fizzpod.smesh.planes.data.Parcel;
import com.fizzpod.smesh.planes.data.ServiceSelector;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

public class JsonPathServiceSelector implements ServiceSelector, Function<Parcel, Boolean>, Serializable {

    private static final long serialVersionUID = 362893196078771648L;
    
    private String query;

    public JsonPathServiceSelector(String query) {
        this.query = query;
    }
    
    @Override
    public Boolean apply(Parcel t) {
        Gson gson = new Gson();
        String json = gson.toJson(t);
        Object result = JsonPath.read(json, query);
        return result != null;
    }

    
}
