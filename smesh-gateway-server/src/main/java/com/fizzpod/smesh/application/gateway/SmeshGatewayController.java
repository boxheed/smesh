package com.fizzpod.smesh.application.gateway;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fizzpod.smesh.planes.data.DataPlane;
import com.fizzpod.smesh.planes.data.Parcel;

@RestController
@RequestMapping("/mesh")
public class SmeshGatewayController {

    @Autowired
    private DataPlane dataPlane;

    @Autowired
    private HttpRequestToMeshRequestMapper inboundMapper;

    @Autowired
    private ParcelToHttpResponseMapper outboundMapper;

    @RequestMapping(path = "/*")
    public ResponseEntity<?> handleRequest(final HttpServletRequest request) throws InterruptedException, ExecutionException {
        Parcel parcel = inboundMapper.map(request);

        Parcel answer = dataPlane.call(parcel);

        ResponseEntity<?> response = outboundMapper.map(answer);

        return response;
    }

}
