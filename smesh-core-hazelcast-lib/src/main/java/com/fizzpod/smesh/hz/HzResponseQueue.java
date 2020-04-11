package com.fizzpod.smesh.hz;

import java.util.concurrent.TimeUnit;

import com.fizzpod.smesh.messaging.Parcel;
import com.hazelcast.collection.IQueue;

public class HzResponseQueue {

    private String queueName;
    private IQueue<Parcel> queue;

    public HzResponseQueue(String queueName, IQueue<Parcel> queue) {
        this.queueName = queueName;
        this.queue = queue;
    }
    
    public String getQueueName() {
        return queueName;
    }
    
    public void post(Parcel parcel) {
        this.queue.add(parcel);
    }
    
    public Parcel take() throws InterruptedException {
        return this.queue.poll(60, TimeUnit.SECONDS);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((queue == null) ? 0 : queue.hashCode());
        result = prime * result + ((queueName == null) ? 0 : queueName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HzResponseQueue other = (HzResponseQueue) obj;
        if (queue == null) {
            if (other.queue != null)
                return false;
        } else if (!queue.equals(other.queue))
            return false;
        if (queueName == null) {
            if (other.queueName != null)
                return false;
        } else if (!queueName.equals(other.queueName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "HzResponseQueue [queueName=" + queueName + ", queue=" + queue + "]";
    }
    
    

}
