package com.spikes2212.prometheus_server;

/**
 * A class that contains all the constants for the project
 */
public class Constants {
    /**
     * An interface that contains all the constants related to Networking
     */
    public static interface NETWORK {
        // constant value for the ServerSocket port
        public static final int PORT = 2212;
    }

    /**
     * An interface that contains all the constants releated to mongodb
     */
    public static interface MONGODB {
        /** the host of the database */
        public static final String HOST = "localhost";
        /** the name of the main database */
        public static final String DB_NAME = "prometheus_db";
        /** the name of the users collection*/
        public static final String USERS_COLLECTION_NAME = "users_coll";
        /** the name of the groups collection*/
        public static final String GROUPS_COLLECTION_NAME = "groups_coll";
        /** the number of the port for mongodb*/
        public static final int PORT = 27017;
    }

    /**
     * An interface that contains all the constants releated to ID generation
     */
    public static interface ID {
        /** The default ASCII code for the biggest digit possible*/
        public static final int MAX_ID_CODE = 126;
        /** The default ASCII code for the lowest digit possible*/
        public static final int MIN_ID_CODE = 48;

        /** The default number of digits for the id*/
        public static final int DEFAULT_ID_SIZE = 12;
    }
}
