package com.fizzpod.smesh.hz.planes.control;

import java.util.regex.Pattern;

import com.fizzpod.smesh.planes.data.ServiceSelector;

public abstract class AbstractRegexServiceSelector implements ServiceSelector {
    
    private Pattern pattern;

    public AbstractRegexServiceSelector(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    
    protected boolean test(String value) {
        return pattern.matcher(value).matches();
    }
}
