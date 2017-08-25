package com.spikes2212.prometheus_server.network;

import com.spikes2212.prometheus_server.Constants;
import com.spikes2212.prometheus_server.util.LogUtil;

import java.io.IOException;

public class ListeningRunnable implements Runnable {

    private Connection connection;
    public ListeningRunnable(Connection connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            while (connection.isReachable(Constants.NETWORK.SOCKET_TIMEOUT)) {
            }
        } catch (IOException ioe) {
            LogUtil.error("IOE while running main listening loop", "");
            ioe.printStackTrace();
        }
    }
}
