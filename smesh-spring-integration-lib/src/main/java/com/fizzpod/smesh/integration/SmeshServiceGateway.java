package com.fizzpod.smesh.integration;

import java.util.concurrent.Future;

public interface SmeshServiceGateway {

    Future<Integer> multiplyByTwo(int i);
    
}
