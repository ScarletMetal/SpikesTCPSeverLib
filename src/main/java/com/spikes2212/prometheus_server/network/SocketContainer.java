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

public class SocketContainer {
    private TypedCollection<Group> groupsCollection;
    private TypedCollection<User>  usersCollection;

    public SocketContainer(TypedCollection<Group> groupsCollection,
                           TypedCollection<User> usersCollection) {
        this.groupsCollection = groupsCollection;
        this.usersCollection = usersCollection;
    }

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
