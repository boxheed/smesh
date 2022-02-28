package com.fizzpod.smesh.hz.planes.control;

import java.io.Serializable;
import java.util.function.Function;

import com.fizzpod.smesh.planes.data.Parcel;

public class MethodServiceSelector extends AbstractRegexServiceSelector implements Function<Parcel, Boolean>, Serializable {

    private static final long serialVersionUID = 362893196078771648L;
    
    public MethodServiceSelector(String query) {
        super(query);
    }
    
    @Override
    public Boolean apply(Parcel t) {
        return super.test(t.getMethod().toString());
    }

    
}
