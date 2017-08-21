package com.spikes2212.prometheus_server;

import com.spikes2212.prometheus_server.util.LogUtil;

public class Main {
    private static void processArguments(String[] args) {
        LogUtil.disable();

        if (args.length > 0 && args[0].equals("--log")) {
            LogUtil.enable();
        }
    }

    private static void mongoInit() {

    }

    public static void main(String[] args) {
        processArguments(args);
        mongoInit();
    }
}
