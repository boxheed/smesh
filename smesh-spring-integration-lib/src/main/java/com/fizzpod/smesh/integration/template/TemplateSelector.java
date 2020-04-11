package com.fizzpod.smesh.integration.template;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fizzpod.smesh.messaging.Parcel;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.io.TemplateSource;

@Component
public class TemplateSelector {

    private Logger LOGGER = LoggerFactory.getLogger(TemplateSelector.class);

    private TemplateLoader templateLoader;

    private List<TemplateSelectorStrategy> templateSelectorStrategyChain;

    @Autowired
    public TemplateSelector(TemplateLoader templateLoader, List<TemplateSelectorStrategy> strategies) {
        this.templateLoader = templateLoader;
        this.templateSelectorStrategyChain = strategies;
    }

    public TemplateSource getTemplateName(Message<Parcel> message) {
        TemplateSource template = null;
        for (TemplateSelectorStrategy selector : templateSelectorStrategyChain) {
            String name = selector.getTemplateName(message);
            template = resolveTemplateSource(name);
            if (template != null) {
                break;
            }
        }
        return template;
    }

    private TemplateSource resolveTemplateSource(String name) {
        TemplateSource template = null;
        if (StringUtils.isNotBlank(name)) {
            try {
                template = templateLoader.sourceAt(name);
            } catch (IOException e) {
                LOGGER.debug("Template {} does not exist.", name);
            }
        }
        return template;
    }

}
