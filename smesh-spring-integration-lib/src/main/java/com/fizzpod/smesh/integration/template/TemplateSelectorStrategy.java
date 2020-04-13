package com.fizzpod.smesh.integration.template;

import org.springframework.messaging.Message;

import com.fizzpod.smesh.messaging.Parcel;

public interface TemplateSelectorStrategy {

    String getTemplateName(Message<Parcel> message);

}
