package com.spikes2212.prometheus_server.util;

/**
 * An Utility that allows colourful logging of info to the terminal
 */
public class LogUtil {
    /**
     * An interface that defines different colors if the ANSI standard
     */
    private static interface ANSI_COLORS {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
    }

    /**
     * Methods that allow to enable and disable the logs
     */
    private static boolean enabled;

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    /**
     * A method that allows to output information about an error in red color. including the number of the current thread
     * @param title the title of the error
     * @param data the data of the error
     */
    public static void error(String title, String data) {
        if (enabled) {
            System.out.println(ANSI_COLORS.ANSI_RED + "Error Log from thread " + Thread.currentThread().getId() + ANSI_COLORS.ANSI_RESET);
            System.out.println(ANSI_COLORS.ANSI_RED + "Error - " + title + " : " + data + ANSI_COLORS.ANSI_RESET);
        }
    }

    /**
     * A method that allows to output data information in green color. including the number of the current thread
     * @param title
     * @param data
     */
    public static void data(String title, String data) {
        if (enabled) {
            System.out.println(ANSI_COLORS.ANSI_GREEN + "Data Log from thread " + Thread.currentThread().getId() + ANSI_COLORS.ANSI_RESET);
            System.out.println(ANSI_COLORS.ANSI_GREEN + "Data - " + title + " : " + data + ANSI_COLORS.ANSI_RESET);
        }
    }
}
