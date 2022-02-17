package com.fizzpod.smesh.gateway;

import java.util.Collection;
import java.util.Map.Entry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import com.fizzpod.smesh.planes.data.Headers;
import com.fizzpod.smesh.planes.data.Parcel;

public class ParcelToHttpResponseMapper {

    public ResponseEntity<?> map(Parcel parcel) {
        
        BodyBuilder builder = createEntity(parcel);
        applyHeaders(builder, parcel);
        return applyBody(builder, parcel);
        
    }

    private ResponseEntity<?> applyBody(BodyBuilder builder, Parcel parcel) {
        Object body = parcel.getBody();
        return builder.body(body);
    }

    private void applyHeaders(BodyBuilder builder, Parcel parcel) {
        Headers headers = parcel.getHeaders();
        for(Entry<String, Collection<String>> entry: headers.getHeaders().entrySet()) {
            Collection<String> values = entry.getValue();
            String headerName = entry.getKey();
            String[] headerValues = new String[entry.getValue().size()];
            headerValues = values.toArray(headerValues); 
            builder.header(headerName, headerValues);
        }
    }

    private BodyBuilder createEntity(Parcel parcel) {
        return ResponseEntity.status(HttpStatus.resolve(parcel.getStatus()));
    }
    

}
