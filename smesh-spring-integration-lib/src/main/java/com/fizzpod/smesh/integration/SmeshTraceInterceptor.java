package com.fizzpod.smesh.integration;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.util.StringUtils;

import com.fizzpod.smesh.messaging.Meta;
import com.fizzpod.smesh.messaging.Parcel;
import com.fizzpod.smesh.messaging.Span;
import com.fizzpod.smesh.messaging.Trace;



/**
 * Intercepts the retrieved message and ensures that it is structured appropriately
 * for the JSON message schema.
 *
 */
public class SmeshTraceInterceptor implements ChannelInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmeshTraceInterceptor.class);

	private String name;
	
	public SmeshTraceInterceptor(String name) {
		this.name = name;
	}
	
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		return updateTrace(message);
	}

	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		//Do nothing.		
	}

	@Override
	public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
		//Do nothing
	}

	@Override
	public boolean preReceive(MessageChannel channel) {
		//Do nothing, need to return true to ensure that the message is retrieved.
		return true;
	}

	@Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		return updateTrace(message);
	}
	
	@Override
	public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
		//Do nothing
	}

	private Message<?> updateTrace(Message<?> message) {
		Object payload = message.getPayload();
		if(payload instanceof Parcel) {
			this.updateTrace((Parcel)payload);
		} else {
			LOGGER.warn("Could not add trace information to message as it isn't formatted as a Parcel: {}", message);
		}
		return message;
	}

	private void updateTrace(Parcel parcel) {
		Trace trace = getTrace(parcel);
		String id = trace.getId();
		if(StringUtils.isEmpty(id)) {
			id = UUID.randomUUID().toString();
			trace.setId(id);
		}
		
		List<Span> spans = trace.getSpans();
		Span currentSpan = getCurrentSpan(spans);
		if(currentSpan == null) {
			currentSpan = createNewSpan(id);
			spans.add(currentSpan);
		} else {
			currentSpan.setOut(DateTime.now());
		}
	}

	private Span getCurrentSpan(List<Span> spans) {
		if(!spans.isEmpty()) {
			Span lastSpan = spans.get(spans.size() - 1);
			if(lastSpan.getOut() == null) {
				return lastSpan;
			}
		}
		return null;
	}

	private Span createNewSpan(String id) {
		Span span = new Span();
		span.setId(id);
		span.setIn(DateTime.now());
		span.setName(name);
		return span;
	}

	private Trace getTrace(Parcel parcel) {
		Meta meta = parcel.getMeta();
		if(meta == null) {
			meta = new Meta();
			parcel.setMeta(meta);
		}
		Trace trace = meta.getTrace();
		if(trace == null) {
			trace = new Trace();
			meta.setTrace(trace);
		}
		return trace;
	}


	
	
}
