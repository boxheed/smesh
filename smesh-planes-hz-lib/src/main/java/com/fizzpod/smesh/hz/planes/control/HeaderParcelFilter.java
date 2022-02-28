package com.fizzpod.smesh.hz.planes.control;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.fizzpod.smesh.planes.data.Headers;
import com.fizzpod.smesh.planes.data.Parcel;
import com.fizzpod.smesh.planes.data.ParcelFilter;

/**
 * header:Add:header_name:header_value
 * header:Replace:header_name_regex:header_value
 * header:Remove:header_name_regex:header_value_regex
 */
public class HeaderParcelFilter implements ParcelFilter, Serializable {

    private static final long serialVersionUID = -195175850341717512L;

    private enum FilterAction {
        REPLACE, REMOVE, ADD
    };

    private FilterAction action;

    private String name_filter;
    private String value_filter;

    public HeaderParcelFilter(String filter) {
        parseFilter(filter);
    }

    private void parseFilter(String filter) {
        String[] parts = StringUtils.split(filter, ":", 3);
        action = FilterAction.valueOf(parts[0]);
        name_filter = parts[1];
        if (parts.length == 3) {
            value_filter = parts[2];
        }
    }

    @Override
    public Parcel apply(Parcel parcel) {
        switch (action) {
        case REPLACE:
            parcel = doReplace(parcel);
            break;
        case REMOVE:
            parcel = doRemove(parcel);
            break;
        case ADD:
            parcel = doAdd(parcel);
            break;
        default:
            break;
        }
        return parcel;
    }

    private Parcel doReplace(Parcel parcel) {
        Headers headers = parcel.getHeaders();
        List<String> removeKeys = new LinkedList<String>();
        Pattern keyPattern = Pattern.compile(name_filter);
        for (String key : headers.getHeaders().keySet()) {
            if (keyPattern.matcher(key).matches()) {
                removeKeys.add(key);
            }
        }
        for (String key : removeKeys) {
            headers.remove(key);
            headers.add(key, value_filter);
        }

        return parcel;
    }

    private Parcel doRemove(Parcel parcel) {
        Headers headers = parcel.getHeaders();
        List<String> removeKeys = new LinkedList<String>();
        Pattern keyPattern = Pattern.compile(name_filter);
        for (String key : headers.getHeaders().keySet()) {
            if (keyPattern.matcher(key).matches()) {
                removeKeys.add(key);
            }
        }
        for (String key : removeKeys) {
            headers.remove(key);
        }
        return parcel;
    }

    private Parcel doAdd(Parcel parcel) {
        Headers headers = parcel.getHeaders();
        headers.add(name_filter, name_filter);
        return parcel;
    }

}
