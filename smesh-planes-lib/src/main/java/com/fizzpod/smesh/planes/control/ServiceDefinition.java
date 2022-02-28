package com.fizzpod.smesh.planes.control;

import com.fizzpod.smesh.planes.data.ParcelFilter;
import com.fizzpod.smesh.planes.data.ServiceSelector;

public interface ServiceDefinition {

    String getName();

    ServiceSelector[] getSelectors();

    ParcelFilter[] getRequestFilters();

    ParcelFilter[] getResponseFilters();

}
