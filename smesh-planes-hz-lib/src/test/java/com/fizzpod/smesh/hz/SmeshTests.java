package com.fizzpod.smesh.hz;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

import com.fizzpod.smesh.hz.planes.control.HzControlPlane;
import com.fizzpod.smesh.hz.planes.control.HzServiceDefinition;
import com.fizzpod.smesh.hz.planes.data.HzDataPlane;
import com.fizzpod.smesh.planes.data.Parcel;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class SmeshTests {

    @Test
    public void testCreateHazelcast() throws InterruptedException, ExecutionException {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        try {
            HzDataPlane dataPlane = new HzDataPlane(instance);
            HzControlPlane controlPlane = new HzControlPlane(instance);
            TestFunction function = new TestFunction();
            controlPlane.registerService(new HzServiceDefinition("apple"), function);
            Assert.assertNotNull(instance.getUserContext().get(HzControlPlane.CONTROL_PLANE_CONTEXT_KEY));
            Parcel parcel = new Parcel();
            Parcel result = dataPlane.call(parcel);
            Assert.assertNotNull(result);
            Assert.assertTrue(function.called);
        } finally {
            if(instance != null) {
                instance.shutdown();
            }
        }
    }
    
    public static class TestFunction implements Function<Parcel, Parcel>, Serializable {

        private static final long serialVersionUID = -8221491998421614111L;

        public boolean called = false;
        
        @Override
        public Parcel apply(Parcel t) {
            called = true;
            return new Parcel();
        }
        
    }
}
