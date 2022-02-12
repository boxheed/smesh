package com.fizzpod.smesh.hz.planes.data;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.fizzpod.smesh.hz.planes.control.HzControlPlane;
import com.fizzpod.smesh.planes.data.DataPlane;
import com.fizzpod.smesh.planes.data.Parcel;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;

public class HzDataPlane implements DataPlane {

    public static final String SMESH_LOCAL_SERVICE = "smesh-local-service";
    public static final String CONTROL_PLANE_CONTEXT_KEY = "control-plane";

    private HazelcastInstance hazelcast;

    public HzDataPlane(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
    }

    @Override
    public Parcel call(Parcel parcel) throws InterruptedException, ExecutionException {
        HzControlPlane controlPlane = getControlPlane();
        IExecutorService executorService = hazelcast.getExecutorService("serviceExecutor");
        HzServiceCaller caller = new HzServiceCaller(parcel);
        Future<Parcel> future = executorService.submit(caller);
        Parcel result = future.get();
        return result;
    }

    private HzControlPlane getControlPlane() {
        ConcurrentMap<String, Object> userContext = hazelcast.getUserContext();
        HzControlPlane controlPlane = (HzControlPlane) userContext.get(HzControlPlane.CONTROL_PLANE_CONTEXT_KEY);
        if (controlPlane == null) {
            throw new RuntimeException("No Control Plane");
        }
        return controlPlane;
    }

}
