package com.spikes2212.prometheus_server.network.message;

import com.spikes2212.prometheus_server.database.TypedCollection;
import com.spikes2212.prometheus_server.network.Connection;
import com.spikes2212.prometheus_server.network.data.Group;
import com.spikes2212.prometheus_server.network.data.User;

import java.util.Map;

public abstract class Message {
    public String id;

    public abstract void fromMap(Map<String, String> map);

    public abstract void process(TypedCollection<User> users,
                                 TypedCollection<Group> group, Connection connection);
}
