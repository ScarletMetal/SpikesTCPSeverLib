package com.spikes2212.prometheus_server;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.spikes2212.prometheus_server.database.TypedCollection;
import com.spikes2212.prometheus_server.network.SocketContainer;
import com.spikes2212.prometheus_server.network.data.Group;
import com.spikes2212.prometheus_server.network.data.User;
import com.spikes2212.prometheus_server.util.LogUtil;

/**
 * The Main class of the PrometheusServer_Server project.
 * This class contains different methods to launch the different components of the project
 */
public class PrometheusServer {

    /**
     * Instances of {@link TypedCollection} that are initialized in {@link PrometheusServer#mongoInit()}
     */
    private static TypedCollection<Group> groupsCollection;
    private static TypedCollection<User> usersCollection;

    /**
     * A method that processes the command line arguments given to the command.
     * currently, the method enables {@link LogUtil} if given the flag "--log"
     * @param args command args given to the program
     */
    private static void processArguments(String[] args) {
        LogUtil.disable();

        if (args.length > 0 && args[0].equals("--log")) {
            LogUtil.enable();
        }
    }

    /**
     * A method that initializes all the components releated to Mongodb in the project.
     * This method creates {@link MongoDatabase} from {@link MongoClient instance}
     * from the {@link MongoDatabase} {@link PrometheusServer#usersCollection} and {@link PrometheusServer#groupsCollection}
     *      are initialized from the {@link MongoDatabase} instance
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
     * A method that creates an instance of {@link SocketContainer}
     * and uses the {@link SocketContainer#startNetworking(int)} method.
     * thus making it the last component of the project starting sequence
     */
    private static void networkInit() {
        SocketContainer container = new SocketContainer(groupsCollection, usersCollection);

        container.startNetworking(Constants.NETWORK.PORT);
    }

    /**
     * The main method of the project.
     * This method contains the starting sequence of the project.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        processArguments(args);
        mongoInit();
        networkInit();
    }
}