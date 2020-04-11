package com.fizzpod.smesh.integration;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MapMessageSplitter {

	public Object splitMessage(Message<?> message) {
		Object payload = message.getPayload(); 
		if(payload instanceof Map) {
			payload = flattenMap((Map) payload);
		}
		return payload;
	}

	private Collection<Object> flattenMap(Map<Object, Object> map) {
		Collection<Object> payloads = new ArrayList<>(); 
		for(Entry<Object, Object> entry: map.entrySet()) {
			Object value = entry.getValue();
			if(value instanceof Collection) {
				payloads.addAll((Collection) value);
			} else if(value instanceof Map) {
				payloads.addAll(flattenMap((Map) value));
			} else {
				payloads.add(value);
			}
		}
		return payloads;
	}
	
}
