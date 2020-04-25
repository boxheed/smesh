package com.fizzpod.smesh.integration;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SmeshParcelHeaderToMessageHeaderMergingInterceptor implements ChannelInterceptor {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SmeshParcelHeaderToMessageHeaderMergingInterceptor.class);

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
        LOGGER.info("Apple {}", message);
        Object payload = message.getPayload();
        if (payload instanceof Parcel) {
            MessageHeaders newHeaders = mergeHeaders(message, (Parcel) payload);
            message = new GenericMessage<Object>(message.getPayload(), newHeaders);
        }
        return message;
    }

    private MessageHeaders mergeHeaders(Message<?> message, Parcel parcel) {
        Headers headers = parcel.getHeaders();
        Map<String, String> parcelHeaders = headers.getHeaders();
        Map<String, Object> newHeaders = new HashMap<>(parcelHeaders);

        for (Entry<String, Object> entry : message.getHeaders().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (newHeaders.containsKey(key)) {
                LOGGER.info("Message Headers contains key {} with value {}, skipping parcel value {}", key,
                        newHeaders.get(value), value);
            }
            newHeaders.put(key, value);
        }
        return new MessageHeaders(newHeaders);
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
