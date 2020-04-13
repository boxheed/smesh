package com.fizzpod.smesh.hz;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fizzpod.smesh.SmeshService;
import com.fizzpod.smesh.SmeshServiceDefinition;
import com.fizzpod.smesh.messaging.Address;
import com.fizzpod.smesh.messaging.Parcel;
import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;

public class HzSmeshService implements SmeshService, Serializable {

    private static final long serialVersionUID = 5293125047655140542L;

    private static final Logger LOGGER = LoggerFactory.getLogger(HzSmeshService.class);

    private HazelcastInstance hazelcast;
    private SmeshServiceDefinition definition;
    private HzResponseQueuePool hzResponseQueuePool;

    public HzSmeshService(HazelcastInstance hazelcast, SmeshServiceDefinition definition,
            HzResponseQueuePool hzResponseQueuePool) {
        this.hazelcast = hazelcast;
        this.definition = definition;
        this.hzResponseQueuePool = hzResponseQueuePool;
    }

    @Override
    public SmeshServiceDefinition getServiceDefinition() {
        return this.definition;
    }

    @Override
    public Parcel call(Parcel parcel) {
        LOGGER.info("Calling service {} with parcel {}", definition, parcel);
        Address recipientAddress = definition.getAddress();
        parcel.setRecipient(recipientAddress);
        HzResponseQueue responseQueue = null;
        Parcel resultParcel = null;
        try {
            responseQueue = hzResponseQueuePool.borrow();
            parcel.getSender().setDestination(responseQueue.getQueueName());
            IQueue<Parcel> queue = hazelcast.getQueue(parcel.getRecipient().getDestination());
            queue.add(parcel);
            resultParcel = responseQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            hzResponseQueuePool.restore(responseQueue);
        }
        return resultParcel;
    }

    @Override
    public void respond(Parcel parcel) {
        String returnQueue = parcel.getSender().getDestination();
        if (returnQueue != null) {
            HzResponseQueue queue = hzResponseQueuePool.getQueue(returnQueue);
            queue.post(parcel);
        } else {
            LOGGER.warn("Return topic not set for message {}", parcel);
        }
    }

    @Override
    public Parcel receive() {
        Parcel parcel = null;
        Address recipientAddress = definition.getAddress();
        try {
            IQueue<Parcel> queue = hazelcast.getQueue(recipientAddress.getDestination());
            parcel = queue.poll(60, TimeUnit.SECONDS);
            LOGGER.info("Retrieved parcel from queue {}, {}", recipientAddress, parcel);
        } catch (InterruptedException e) {
            LOGGER.error("An error occurred polling queue {}", recipientAddress, e);
        }
        return parcel;
    }

}
