package com.lgcode.popularshows.bus;

import com.squareup.otto.Bus;

/**
 * Created by leojg on 11/9/16.
 */
public class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
    }
}
