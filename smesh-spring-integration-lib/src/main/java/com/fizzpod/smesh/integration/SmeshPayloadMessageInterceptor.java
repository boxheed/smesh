package com.fizzpod.smesh.integration;

import java.util.Map.Entry;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.GenericMessage;

import com.fizzpod.smesh.messaging.Headers;
import com.fizzpod.smesh.messaging.Meta;
import com.fizzpod.smesh.messaging.Parcel;


/**
 * Intercepts the retrieved message and ensures that it is structured
 * appropriately for the JSON message schema.
 *
 */
public class SmeshPayloadMessageInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return processMessage(message);
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        // Do nothing.
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        // Do nothing
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        // Do nothing, need to return true to ensure that the message is retrieved.
        return true;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        return processMessage(message);
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        // Do nothing
    }
    
    private Message<?> processMessage(Message<?> message) {
        Object payload = message.getPayload();
        Parcel parcel = this.decoratePayload(payload);
        //ensure the correct destination service is set
        setDestinationServiceName(message, parcel);
        //ensure the headers are copied into the parcel
        copyHeaders(message, parcel);
        return new GenericMessage<Object>(parcel, message.getHeaders());
    }

    private void copyHeaders(Message<?> message, Parcel parcel) {
        Headers headers = parcel.getHeaders();
        
        MessageHeaders messageHeaders = message.getHeaders();
        for(Entry<String, Object> entry: messageHeaders.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            headers.add(key, value.toString());
        }
    }

    private void setDestinationServiceName(Message<?> message, Parcel parcel) {
        String serviceName = message.getHeaders().get("X-SMESH-SERVICE-NAME").toString();
        parcel.getRecipient().setName(serviceName);
    }

    private Parcel decoratePayload(Object payload) {
        if (isPayloadAlreadyDecorated(payload)) {
            return (Parcel) payload;
        }
        Parcel parcel = new Parcel();
        Meta meta = new Meta();
        parcel.setMeta(meta);
        parcel.setPayload(payload);
        return parcel;
    }

    private boolean isPayloadAlreadyDecorated(Object payload) {
        if (payload instanceof Parcel) {
            return true;
        }
        return false;
    }

}
