package com.fizzpod.smesh.hz.planes.data;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.fizzpod.smesh.hz.planes.control.HzControlPlane;
import com.fizzpod.smesh.hz.planes.control.HzRouter;
import com.fizzpod.smesh.planes.data.DataPlane;
import com.fizzpod.smesh.planes.data.Parcel;
import com.hazelcast.cluster.Member;
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
        Parcel result = callService(parcel);
        return result;
    }

    private Parcel callService(Parcel parcel) throws InterruptedException, ExecutionException {
        HzServiceCaller caller = getCaller(parcel);
        HzRoute route = getRoute(parcel);
        Map<Member, Future<Parcel>> futures = submit(caller, route);
        return waitForResult(futures);
    }

    private HzServiceCaller getCaller(Parcel parcel) {
        HzServiceCaller caller = new HzServiceCaller(parcel);
        return caller;
    }

    private HzRoute getRoute(Parcel parcel) {
        HzControlPlane controlPlane = getControlPlane();
        HzRouter router = (HzRouter) controlPlane.getRouter();
        HzRoute route = router.getRoute(parcel);
        return route;
    }

    private Map<Member, Future<Parcel>> submit(HzServiceCaller caller, HzRoute route) {
        IExecutorService executorService = hazelcast.getExecutorService("serviceExecutor");
        Map<Member, Future<Parcel>> futures = executorService.submitToMembers(caller, route);
        return futures;
    }

    private Parcel waitForResult(Map<Member, Future<Parcel>> futures) throws InterruptedException, ExecutionException {
        Parcel result = null;
        for (Entry<Member, Future<Parcel>> entry : futures.entrySet()) {
            // TODO cope with exceptions
            result = entry.getValue().get();
        }
        return result;
    }


    private HzControlPlane getControlPlane() {
        ConcurrentMap<String, Object> userContext = hazelcast.getUserContext();
        HzControlPlane controlPlane = (HzControlPlane) userContext.get(HzControlPlane.CONTROL_PLANE_CONTEXT_KEY);
        if (controlPlane == null) {
            //TODO change this to some kind of managed exception
            throw new RuntimeException("No Control Plane");
        }
        return controlPlane;
    }

}
