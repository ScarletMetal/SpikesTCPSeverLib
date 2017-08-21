package com.spikes2212.prometheus_server.network;

import com.mongodb.client.MongoDatabase;
import com.spikes2212.prometheus_server.Constants;
import com.spikes2212.prometheus_server.database.TypedCollection;
import com.spikes2212.prometheus_server.network.data.Group;
import com.spikes2212.prometheus_server.network.data.User;

public class SocketContainer {
    private TypedCollection<Group> groupsCollection;
    private TypedCollection<User>  usersCollection;

    public SocketContainer(TypedCollection<Group> groupsCollection,
                           TypedCollection<User> usersCollection) {
        this.groupsCollection = groupsCollection;
        this.usersCollection = usersCollection;
    }
}
