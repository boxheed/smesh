package com.fizzpod.smesh.sidecar;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.function.Function;

import com.fizzpod.smesh.hz.planes.control.HzServiceDefinition;
import com.fizzpod.smesh.planes.control.ServiceDefinition;
import com.fizzpod.smesh.planes.data.Parcel;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class SidecarServiceFunction implements Function<Parcel, Parcel> {

    private final OkHttpClient client = new OkHttpClient();

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
        //TODO figure out the problem with the headers
//        builder = addHeaders(builder, parcel);
        builder = addBody(builder, parcel);
        return builder.build();
    }

    private Builder addUrl(Builder builder, Parcel parcel) {
        String path = parcel.getAddress();
        // TODO update this URL with the real path bits need to be injected in as
        // configuration
        return new Request.Builder().url("https://sg86bqygsk.execute-api.eu-west-2.amazonaws.com/hello");
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

    public ServiceDefinition getDefinition() {
        Collection<String> routes = Arrays.asList(new String[] { "/testy.*" });
        HzServiceDefinition definition = new HzServiceDefinition(this.getClass().getName(), routes);
        return definition;
    }

}
