package com.fizzpod.smesh.hz.planes.control;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fizzpod.smesh.hz.planes.data.HzRoute;
import com.fizzpod.smesh.planes.control.Router;
import com.fizzpod.smesh.planes.data.Parcel;
import com.hazelcast.core.HazelcastInstance;

public class HzRouter implements Router, Serializable {

    private static final long serialVersionUID = 8532259110450928975L;
    private HazelcastInstance hazelcast;
    private Set<HzServiceEntry> serviceRoutes;

    public HzRouter(HazelcastInstance hazelcast, Set<HzServiceEntry> serviceRoutes) {
        this.hazelcast = hazelcast;
        this.serviceRoutes = serviceRoutes;
    }

    public HzRoute getRoute(Parcel parcel) {
//        List<UUID> memberIds = new LinkedList<>();
//        String address = parcel.getAddress();
//        for (HzServiceEntry entry : serviceRoutes) {
//            for (String route : entry.getDefinition().getRoutes()) {
//                if (address.matches(route)) {
//                    memberIds.add(entry.getMemberId());
//                    break;
//                }
//            }
//        }
//        Collections.shuffle(memberIds);
//        if(memberIds.size() == 0) {
//            throw new RuntimeException("No route to" + parcel.getAddress());
//        }
//        return new HzRoute(hazelcast, Collections.singleton(memberIds.get(0)));
        return null;
    }

}
