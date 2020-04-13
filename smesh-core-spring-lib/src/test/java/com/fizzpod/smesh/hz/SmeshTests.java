package com.fizzpod.smesh.hz;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class SmeshTests {

    @Test
    public void testCreateHazelcast() {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        try {
//            IQueue<?> queue = instance.getQueue(HzResponseQueuePool.HZ_RESPONSE_QUEUE_PREFIX + "test");
//            assertNotNull(queue);
            Config config = instance.getConfig();
            assertNotNull(config);
        } finally {
            if(instance != null) {
                instance.shutdown();
            }
        }
    }
}
