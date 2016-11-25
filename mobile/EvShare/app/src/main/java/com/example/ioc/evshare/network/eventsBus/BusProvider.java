package com.example.ioc.evshare.network.eventsBus;

import com.squareup.otto.Bus;


public class BusProvider {
    private static Bus instance;

    private BusProvider() {
    }

    public static Bus bus() {
        if (instance == null) {
            instance = new Bus();
        }
        return instance;
    }

}
