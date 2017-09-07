package com.spikes2212.prometheus_server;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.spikes2212.prometheus_server.database.TypedCollection;
import com.spikes2212.prometheus_server.network.SocketContainer;
import com.spikes2212.prometheus_server.network.data.Group;
import com.spikes2212.prometheus_server.network.data.User;
import com.spikes2212.prometheus_server.util.LogUtil;


public class Main {

    private static TypedCollection<Group> groupsCollection;
    private static TypedCollection<User> usersCollection;

    private static void processArguments(String[] args) {
        LogUtil.disable();

        if (args.length > 0 && args[0].equals("--log")) {
            LogUtil.enable();
        }
    }

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
    private static void networkInit() {
        SocketContainer container = new SocketContainer(groupsCollection, usersCollection);

        container.startNetworking(Constants.NETWORK.PORT);
    }
    public static void main(String[] args) {
        processArguments(args);
        LogUtil.data("Test", "test");
    }
}
