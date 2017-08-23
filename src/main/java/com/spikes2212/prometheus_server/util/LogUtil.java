package com.spikes2212.prometheus_server.util;

public class LogUtil {
    private static interface ANSI_COLORS {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
    }

    private static boolean enabled;

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    public static void error(String title, String data) {
        if (enabled) {
            System.out.println(ANSI_COLORS.ANSI_RED + "Error Log from thread " + Thread.currentThread().getId() + ANSI_COLORS.ANSI_RESET);
            System.out.println(ANSI_COLORS.ANSI_RED + "Error - " + title + " : " + data + ANSI_COLORS.ANSI_RESET);
        }
    }

    public static void data(String title, String data) {
        if (enabled) {
            System.out.println(ANSI_COLORS.ANSI_GREEN + "Data Log from thread " + Thread.currentThread().getId() + ANSI_COLORS.ANSI_RESET);
            System.out.println(ANSI_COLORS.ANSI_GREEN + "Data - " + title + " : " + data + ANSI_COLORS.ANSI_RESET);
        }
    }
}
