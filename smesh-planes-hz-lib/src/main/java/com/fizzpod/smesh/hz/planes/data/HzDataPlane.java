package com.fizzpod.smesh.hz.planes.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.fizzpod.smesh.hz.planes.control.HzControlPlane;
import com.fizzpod.smesh.planes.data.DataPlane;
import com.fizzpod.smesh.planes.data.Parcel;
import com.hazelcast.cluster.Member;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;

public class HzDataPlane implements DataPlane {

    public static final String SMESH_LOCAL_SERVICE = "smesh-local-service";
    public static final String SMESH_LOCAL_SERVICE_HEALTH = "smesh-local-service-health";
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
        Member route = getRoute(parcel);
        Future<Parcel> futures = submit(caller, route);
        return waitForResult(futures);
    }

    private Member getRoute(Parcel parcel) {
        IExecutorService executorService = hazelcast.getExecutorService("route-executor");
        HzMemberSelector selector = new HzMemberSelector(parcel);
        Map<Member, Future<UUID>> futures = executorService.submitToAllMembers(selector);
        List<Member> matches = new ArrayList<>();
        for (Entry<Member, Future<UUID>> future : futures.entrySet()) {
            try {
                UUID uuid = future.getValue().get();
                if (uuid != null) {
                    matches.add(future.getKey());
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Collections.shuffle(matches);
        if (matches.size() == 0) {
            throw new RuntimeException("Could not match service");
        }
        return matches.get(0);
    }

    private HzServiceCaller getCaller(Parcel parcel) {
        HzServiceCaller caller = new HzServiceCaller(parcel);
        return caller;
    }

    private Future<Parcel> submit(HzServiceCaller caller, Member route) {
        IExecutorService executorService = hazelcast.getExecutorService("service-executor");
        Future<Parcel> future = executorService.submitToMember(caller, route);
        return future;
    }

    private Parcel waitForResult(Future<Parcel> future) throws InterruptedException, ExecutionException {
        return future.get();
    }

    private HzControlPlane getControlPlane() {
        ConcurrentMap<String, Object> userContext = hazelcast.getUserContext();
        HzControlPlane controlPlane = (HzControlPlane) userContext.get(HzControlPlane.CONTROL_PLANE_CONTEXT_KEY);
        if (controlPlane == null) {
            // TODO change this to some kind of managed exception
            throw new RuntimeException("No Control Plane");
        }
        return controlPlane;
    }

}
