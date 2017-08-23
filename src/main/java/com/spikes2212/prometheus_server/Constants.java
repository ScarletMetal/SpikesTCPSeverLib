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

    public static interface ID {
        public static final int MAX_ID_CODE = 126;
        public static final int MIN_ID_CODE = 48;

        public static final int DEFAULT_ID_SIZE = 12;
    }
}
