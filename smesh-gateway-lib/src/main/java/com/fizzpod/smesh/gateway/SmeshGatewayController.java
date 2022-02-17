package com.fizzpod.smesh.gateway;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fizzpod.smesh.planes.data.DataPlane;
import com.fizzpod.smesh.planes.data.Parcel;

@RestController
@RequestMapping(SmeshGatewayController.CONTROLLER_PATH)
public class SmeshGatewayController {
    
    public static final String CONTROLLER_PATH = "/mesh";

    @Autowired
    private DataPlane dataPlane;

    @Autowired
    private HttpRequestToParcelMapper inboundMapper;

    @Autowired
    private ParcelToHttpResponseMapper outboundMapper;

    @RequestMapping(path = "*")
    public ResponseEntity<?> handleRequest(final HttpServletRequest request) throws InterruptedException, ExecutionException {
        Parcel parcel = inboundMapper.map(request);

        Parcel answer = dataPlane.call(parcel);

        ResponseEntity<?> response = outboundMapper.map(answer);

        return response;
    }

}
