package com.fizzpod.smesh.hz.planes.control;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.function.Function;

import com.fizzpod.smesh.planes.data.Headers;
import com.fizzpod.smesh.planes.data.Parcel;

public class HeaderServiceSelector extends AbstractRegexServiceSelector
        implements Function<Parcel, Boolean>, Serializable {

    private static final long serialVersionUID = 362893196078771648L;

    private String query;

    public HeaderServiceSelector(String query) {
        super(query);
    }

    @Override
    public Boolean apply(Parcel t) {
        Headers headers = t.getHeaders();
        boolean result = false;
        if (headers.getHeaders().size() > 0) {
            result = true;
            for (Entry<String, Collection<String>> header : headers.getHeaders().entrySet()) {
                String key = header.getKey();
                for (String value : header.getValue()) {
                    if (!super.test(key + ": " + value)) {
                        return false;
                    }
                }
            }
        }
        return result;
    }

}
