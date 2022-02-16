package com.fizzpod.smesh.hz.planes.data;

import java.util.Collection;
import java.util.UUID;

import com.hazelcast.cluster.Member;
import com.hazelcast.cluster.MemberSelector;
import com.hazelcast.core.HazelcastInstance;

public class HzRoute implements MemberSelector {

    private HazelcastInstance hazelcast;
    private Collection<UUID> members;


    public HzRoute(HazelcastInstance hazelcast, Collection<UUID> members) {
        this.hazelcast = hazelcast;
        this.members = members;
    }
    
    
    @Override
    public boolean select(Member member) {
        return members.contains(member.getUuid());
    }

}
