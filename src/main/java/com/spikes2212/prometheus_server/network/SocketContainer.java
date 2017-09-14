package com.spikes2212.prometheus_server.network;

import com.mongodb.client.MongoDatabase;
import com.spikes2212.prometheus_server.Constants;
import com.spikes2212.prometheus_server.database.TypedCollection;
import com.spikes2212.prometheus_server.network.data.Group;
import com.spikes2212.prometheus_server.network.data.User;
import com.spikes2212.prometheus_server.util.LogUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A class that wraps {@link ServerSocket} instance and it's listening loop
 * @see ServerSocket
 * @see com.spikes2212.prometheus_server.PrometheusServer
 * @author Simon "C" Kharmatsky
 */
public class SocketContainer {
    /**
     * {@link TypedCollection} instances that are passed to {@link ListeningRunnable} instance
     */
    private TypedCollection<Group> groupsCollection;
    private TypedCollection<User>  usersCollection;

    /**
     * Constructs new {@link SocketContainer} instance that receives two {@link TypedCollection} instances
     * @param groupsCollection {@link TypedCollection} instance for {@link Group} instances
     * @param usersCollection {@link TypedCollection} instance for {@link User} instances
     */
    public SocketContainer(TypedCollection<Group> groupsCollection,
                           TypedCollection<User> usersCollection) {
        this.groupsCollection = groupsCollection;
        this.usersCollection = usersCollection;
    }

    /**
     * A method that start the listening loop of an {@link ServerSocket} instance at the given port
     * @param port the port that {@link ServerSocket} instance will opened on
     */
    public void startNetworking(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            LogUtil.data("startNetworking", "created server socket successfully");
            while (true) {
                Socket socket = server.accept();
                LogUtil.data("new client", "accepted new client");
                Connection connection = new Connection(socket);
                connection.startListeningThread(groupsCollection, usersCollection);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.error("IOE in startNetworking", e.toString());
        }
    }
}
