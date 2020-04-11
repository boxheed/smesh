package com.fizzpod.smesh.hz;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.fizzpod.smesh.messaging.Parcel;
import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;

public class HzResponseQueuePool {

    public static String HZ_RESPONSE_QUEUE_PREFIX = "smesh-service-queue-response-";
    public static String HZ_RESPONSE_QUEUE_REGISTER = "smesh-service-queue-response-register";
    public static int QUEUE_SEED = 10;
    
    private IQueue<String> responseQueueRegister;
    private HazelcastInstance hazelcast;
    
    public HzResponseQueuePool(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
        //seed the queue
        responseQueueRegister = hazelcast.getQueue(HZ_RESPONSE_QUEUE_REGISTER);
        //TODO need to ensure that the queue registry doesn't get too many queues
        if(responseQueueRegister.isEmpty()) {
            for(int i = 0; i < QUEUE_SEED; i++) {
                String suffix = UUID.randomUUID().toString();
                try {
                    responseQueueRegister.put(HZ_RESPONSE_QUEUE_PREFIX + suffix);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    public HzResponseQueue borrow() throws InterruptedException {
        String queueName = responseQueueRegister.poll(10, TimeUnit.SECONDS);
        return this.getQueue(queueName);
    }
    
    public void restore(HzResponseQueue queue) {
        if(queue != null) {
            String queueName = queue.getQueueName();
            this.responseQueueRegister.add(queueName);
        }
    }
    
    public HzResponseQueue getQueue(String queueName) {
        IQueue<Parcel> queue = hazelcast.getQueue(queueName);
        return new HzResponseQueue(queueName, queue);
    }
}
