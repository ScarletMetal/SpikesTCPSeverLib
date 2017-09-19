package com.spikes2212.prometheus_server;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.spikes2212.prometheus_server.database.TypedCollection;
import com.spikes2212.prometheus_server.network.SocketContainer;
import com.spikes2212.prometheus_server.network.data.Group;
import com.spikes2212.prometheus_server.network.data.User;
import com.spikes2212.prometheus_server.util.LogUtil;

/**
 * <p>
 * The Main class of the PrometheusServer_Server project.
 * This class contains different methods to launch the different components of the project
 * </p>
 */
public class PrometheusServer {

    private static TypedCollection<Group> groupsCollection;
    private static TypedCollection<User> usersCollection;

    /**
     * <p>
     *  This method processes the command line arguments given to the program.
     *  if the flag --log is given uses {@link LogUtil#enable()} to enable colored logs to the terminal.
     *  This method is the first in the project startup sequence
     * </p>
     * @param args command args given to the program
     */
    private static void processArguments(String[] args) {
        LogUtil.disable();

        if (args.length > 0 && args[0].equals("--log")) {
            LogUtil.enable();
        }
    }

    /**
     * <p>
     *  This method initializes all the components releated to Mongodb in the project.
     *  It creates {@link MongoDatabase} from {@link MongoClient instance}
     *  from the {@link MongoDatabase} {@link PrometheusServer#usersCollection} and {@link PrometheusServer#groupsCollection}
     *  are initialized.
     *  This method is the second in the project startup sequence
     *  </p>
     */
    private static void mongoInit() {
        MongoClient client = new MongoClient(Constants.MONGODB.HOST, Constants.MONGODB.PORT);

        MongoDatabase mainDB = client.getDatabase(Constants.MONGODB.DB_NAME);
        LogUtil.data("Loading main db", "success");

        usersCollection =
                new TypedCollection<User>(mainDB.getCollection(Constants.MONGODB.USERS_COLLECTION_NAME));
        groupsCollection =
                new TypedCollection<Group>(mainDB.getCollection(Constants.MONGODB.GROUPS_COLLECTION_NAME));
        LogUtil.data("monogo initialization complete", "success");
    }

     /**
     * <p>
     *  This method creates an instance of {@link SocketContainer}
     *  and uses the {@link SocketContainer#startNetworking(int)} method.
     *  This method is the last in the project startup sequence
     * </p>
     */
    private static void networkInit() {
        SocketContainer container = new SocketContainer(groupsCollection, usersCollection);

        container.startNetworking(Constants.NETWORK.PORT);
    }

    /**
     * <p>
     *  The main method of the project.
     *  This method initializes the project according to the startup sequence mentioned above
     * </p>
     * @param args command line arguments
     */
    public static void main(String[] args) {
        processArguments(args);
        mongoInit();
        networkInit();
    }
}
