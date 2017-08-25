package com.spikes2212.prometheus_server.network;

import com.spikes2212.prometheus_server.Constants;
import com.spikes2212.prometheus_server.util.LogUtil;

import java.io.IOException;

public class ListenningRunnable implements Runnable {

    private Connection connection;
    public ListenningRunnable(Connection connection) {
        this.connection = connection;
    }

    public void run() {

    }
}
