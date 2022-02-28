package com.fizzpod.smesh.hz.planes.control;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fizzpod.smesh.planes.data.Parcel;
import com.fizzpod.smesh.planes.data.ParcelFilter;
import com.hazelcast.auditlog.Level;

public class LoggingParcelFilter implements ParcelFilter, Serializable {

    private static final long serialVersionUID = 2652518197574453429L;

    private static final Logger logger = LoggerFactory.getLogger(LoggingParcelFilter.class);
    
    private Level level;
    
    public LoggingParcelFilter(String level) {
        this.level = Level.valueOf(level);
    }
    
    @Override
    public Parcel apply(Parcel parcel) {
        switch(level) {
        case INFO: logger.info("{}", parcel); break;
        case DEBUG: logger.debug("{}", parcel); break;
        case ERROR: logger.error("{}", parcel); break;
        case WARN: logger.warn("{}", parcel); break;
        default: logger.trace("{}", parcel); break;
        }
        return parcel;
    }

    
    
}
