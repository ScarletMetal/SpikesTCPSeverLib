package com.spikes2212.prometheus_server.util;

/**
 * <p>
 *     An Utility that allows colourful logging of info to the terminal
 * </p>
 */
public class LogUtil {
    private static interface ANSI_COLORS {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
    }
    private static boolean enabled;

    /**
     * <p>
     *     A method that enables logs to the command line
     * </p>
     */
    public static void enable() {
        enabled = true;
    }

    /**
     * <p>
     *     A method that disables logs to the command line
     * </p>
     */
    public static void disable() {
        enabled = false;
    }

    /**
     * <p>
     *    A method that allows to output information about an error in red color.
     *    including the number of the current thread
     * </p>
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
     * <p>
     *     A method that allows to output data information in green color. including the number of the current thread
     * </p>
     * @param title the title of the message
     * @param data the data of the message
     */
    public static void data(String title, String data) {
        if (enabled) {
            System.out.println(ANSI_COLORS.ANSI_GREEN + "Data Log from thread " + Thread.currentThread().getId() + ANSI_COLORS.ANSI_RESET);
            System.out.println(ANSI_COLORS.ANSI_GREEN + "Data - " + title + " : " + data + ANSI_COLORS.ANSI_RESET);
        }
    }
}
