package com.fizzpod.smesh.application.gateway;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fizzpod.smesh.planes.data.Headers;
import com.fizzpod.smesh.planes.data.Parameters;
import com.fizzpod.smesh.planes.data.Parcel;

public class HttpRequestToMeshRequestMapper {

    public Parcel map(HttpServletRequest request) {
        Parcel meshRequest = new Parcel();
        copyHeaders(request, meshRequest);
        copyBody(request, meshRequest);
        copyPath(request, meshRequest);
        copyMethod(request, meshRequest);
        copyIp(request, meshRequest);
        return meshRequest;
    }

    private void copyIp(HttpServletRequest request, Parcel meshRequest) {
        // TODO skip for now
    }

    private void copyMethod(HttpServletRequest request, Parcel meshRequest) {
        String methodStr = request.getMethod();
        if (StringUtils.isNotBlank(methodStr)) {
            methodStr = methodStr.trim().toUpperCase();
            Parcel.Method method = Parcel.Method.valueOf(methodStr);
            meshRequest.setMethod(method);
        }
    }

    private void copyPath(HttpServletRequest request, Parcel meshRequest) {
        
        String path = request.getRequestURI();
        path = StringUtils.removeStart(path, SmeshGatewayController.CONTROLLER_PATH);
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(path);
        builder.query(request.getQueryString());
        UriComponents components = builder.build();
        meshRequest.setPath(components.getPath());
        Parameters parameters = new Parameters();
        MultiValueMap<String, String> queries = components.getQueryParams();
        for (Entry<String, List<String>> entry : queries.entrySet()) {
            parameters.addAll(entry.getKey(), entry.getValue());
        }
        meshRequest.setParameters(parameters);
    }

    private void copyBody(HttpServletRequest request, Parcel meshRequest) {
        try {
            if (request.getContentLength() > 0) {
                byte[] content = IOUtils.toByteArray(request.getInputStream());
                meshRequest.setBody(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyHeaders(HttpServletRequest request, Parcel meshRequest) {
        Headers headers = new Headers();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            Enumeration<String> headerValues = request.getHeaders(key);
            while (headerValues.hasMoreElements()) {
                String value = headerValues.nextElement();
                headers.add(key, value);
            }
        }
        meshRequest.setHeaders(headers);
    }
}
