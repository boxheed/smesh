package com.fizzpod.smesh.sidecar;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fizzpod.smesh.hz.planes.control.HzServiceDefinition;
import com.fizzpod.smesh.hz.planes.control.HzServiceDefinitionBuilder;
import com.fizzpod.smesh.planes.control.ServiceDefinition;
import com.fizzpod.smesh.planes.data.Parameters;
import com.fizzpod.smesh.planes.data.Parcel;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class SidecarServiceFunction implements Function<Parcel, Parcel> {

    private final OkHttpClient client = new OkHttpClient();
    private String baseUrl;
    
    public SidecarServiceFunction(String url) {
        this.baseUrl = url;
    }

    @Override
    public Parcel apply(Parcel input) {
        Parcel output = new Parcel();
        Request request = createRequest(input);
        try {
            Response response = client.newCall(request).execute();
            output = createOutput(response, input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;

    }

    private Parcel createOutput(Response response, Parcel input) {
        return new OkHttpResponseToParcelMapper().map(response);
    }

    private Request createRequest(Parcel parcel) {
        Builder builder = new Request.Builder();
        builder = addUrl(builder, parcel);
        builder = addHeaders(builder, parcel);
        builder = addBody(builder, parcel);
        return builder.build();
    }

    private Builder addUrl(Builder builder, Parcel parcel) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(baseUrl);
        urlBuilder.path(parcel.getAddress());
        String url = urlBuilder.build().encode().toUriString();
        
        return new Request.Builder().url(url);
    }

    private Builder addHeaders(Builder builder, Parcel parcel) {
        for (Entry<String, Collection<String>> header : parcel.getHeaders().getHeaders().entrySet()) {
            String key = header.getKey();
            for (String value : header.getValue()) {
                builder = builder.addHeader(key, value);
            }
        }
        return builder;
    }

    private Builder addBody(Builder builder, Parcel parcel) {
        // TODO do other methods
        Parcel.Method method = parcel.getMethod();
        switch (method) {
        case GET:
            builder = builder.get();
            break;
        default:
            builder = builder.get();
            break;
        }
        return builder;
    }

}
