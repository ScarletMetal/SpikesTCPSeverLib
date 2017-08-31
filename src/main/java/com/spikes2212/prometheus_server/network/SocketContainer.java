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
            while (true) {
                Socket socket = server.accept();
                Connection connection = new Connection(socket);
                connection.startListeningThread();
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.error("IOE in startNetworking", e.toString());
        }
    }
}
