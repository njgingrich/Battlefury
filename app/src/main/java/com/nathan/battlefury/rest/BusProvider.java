package com.nathan.battlefury.rest;

import com.squareup.otto.Bus;

/**
 * Created by nathan on 6/3/15.
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}