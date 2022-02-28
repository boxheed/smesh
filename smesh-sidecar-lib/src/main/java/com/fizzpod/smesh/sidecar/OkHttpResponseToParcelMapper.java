package com.fizzpod.smesh.sidecar;

import java.io.IOException;

import com.fizzpod.smesh.planes.data.Headers;
import com.fizzpod.smesh.planes.data.Parcel;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpResponseToParcelMapper {

    public Parcel map(Response response) {
        Parcel meshResponse = new Parcel();
        copyHeaders(response, meshResponse);
        copyBody(response, meshResponse);
        copyResponseCode(response, meshResponse);
        return meshResponse;
    }

    private void copyResponseCode(Response response, Parcel meshResponse) {
        int responseCode = response.code();
        meshResponse.setStatus(responseCode);
    }

    private void copyBody(Response response, Parcel meshResponse) {
        ResponseBody body = response.body();
//        if(body.contentLength() > 0) {
        try {
            byte[] bodyBytes = body.bytes();
            meshResponse.setBody(bodyBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        }
    }

    private void copyHeaders(Response response, Parcel meshResponse) {
        Headers meshHeaders = new Headers();
        for (String headerName : response.headers().names()) {
            for (String headerValue : response.headers(headerName)) {
                meshHeaders.add(headerName, headerValue);
            }
        }
        meshResponse.setHeaders(meshHeaders);
    }
}
