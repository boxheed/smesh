package com.fizzpod.smesh.services.experimental.cmdline;

import com.fizzpod.smesh.Service;
import com.fizzpod.smesh.messaging.Parcel;

public class SmeshCmdlineService implements Service {

    @Override
    public Parcel call() throws Exception {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getConcurrency() {
        return 0;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

}
