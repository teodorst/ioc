package com.example.ioc.evshare.network.actionsBus;

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
