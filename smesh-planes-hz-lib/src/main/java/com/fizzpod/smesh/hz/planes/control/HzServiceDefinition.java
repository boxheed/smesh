package com.fizzpod.smesh.hz.planes.control;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.fizzpod.smesh.planes.control.ServiceDefinition;
import com.fizzpod.smesh.planes.data.Parcel;
import com.fizzpod.smesh.planes.data.ParcelFilter;
import com.fizzpod.smesh.planes.data.ServiceSelector;

public class HzServiceDefinition implements ServiceDefinition, Serializable {

    private static final long serialVersionUID = -3894325186282124626L;

    private String name;

    private ServiceSelector[] selectors = new ServiceSelector[0];

    private ParcelFilter[] requestFilters = new ParcelFilter[0];

    private ParcelFilter[] responseFilters = new ParcelFilter[0];

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ServiceSelector[] getSelectors() {
        return this.selectors;
    }

    public void setSelectors(List<ServiceSelector> selectors) {
        this.selectors = selectors.toArray(new ServiceSelector[selectors.size()]);
    }

    @Override
    public ParcelFilter[] getRequestFilters() {
        return this.requestFilters;
    }

    public void setRequestFilters(List<ParcelFilter> requestFilters) {
        this.requestFilters = requestFilters.toArray(new ParcelFilter[requestFilters.size()]);
    }

    @Override
    public ParcelFilter[] getResponseFilters() {
        return this.responseFilters;
    }

    public void setResponseFilters(List<ParcelFilter> responseFilters) {
        this.responseFilters = responseFilters.toArray(new ParcelFilter[responseFilters.size()]);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Arrays.hashCode(requestFilters);
        result = prime * result + Arrays.hashCode(responseFilters);
        result = prime * result + Arrays.hashCode(selectors);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HzServiceDefinition other = (HzServiceDefinition) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (!Arrays.equals(requestFilters, other.requestFilters))
            return false;
        if (!Arrays.equals(responseFilters, other.responseFilters))
            return false;
        if (!Arrays.equals(selectors, other.selectors))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "HzServiceDefinition [name=" + name + ", selectors=" + Arrays.toString(selectors) + ", requestFilters="
                + Arrays.toString(requestFilters) + ", responseFilters=" + Arrays.toString(responseFilters) + "]";
    }

}
