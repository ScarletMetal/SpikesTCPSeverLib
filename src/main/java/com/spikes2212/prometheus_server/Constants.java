package com.spikes2212.prometheus_server;

public class Constants {
    public static interface NETWORK {
        public static final int PORT = 2212;
    }

    public static interface MONGODB {
        public static final String HOST = "localhost";
        public static final String GROOPS_DB_NAME = "rooms_db";
        public static final String USERS_DB_NAME = "users_db";
        public static final int PORT = 27017;
    }
}
