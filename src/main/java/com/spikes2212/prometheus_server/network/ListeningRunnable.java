package com.spikes2212.prometheus_server.network;

import com.spikes2212.prometheus_server.database.TypedCollection;
import com.spikes2212.prometheus_server.network.data.Group;
import com.spikes2212.prometheus_server.network.data.User;
import com.spikes2212.prometheus_server.network.message.Message;
import com.spikes2212.prometheus_server.network.message.UnknownMessageTypeException;
import com.spikes2212.prometheus_server.util.JsonUtil;
import com.spikes2212.prometheus_server.util.LogUtil;

import java.io.IOException;

public class ListeningRunnable implements Runnable {

    private Connection connection;
    private TypedCollection<Group> groupsCollection;
    private TypedCollection<User> usersCollection;
    public ListeningRunnable(Connection connection, TypedCollection<Group> groupsCollection,
                             TypedCollection<User> usersCollection) {
        this.connection = connection;
        this.groupsCollection = groupsCollection;
        this.usersCollection = usersCollection;
    }

    public void run() {
        try {
            String data;
            while ((data = connection.readLine()) != null) {
                Message msg = JsonUtil.fromJson(data, Message.class);
            }
        } catch (IOException ioe) {
            LogUtil.error("IOE while runninprocessMessage(msg);g main listening loop", "");
            ioe.printStackTrace();
        }
    }
    private void processMessage(Object msg) throws UnknownMessageTypeException {
        if (!(msg instanceof Message)) {
            throw new UnknownMessageTypeException(msg);
        }

        Message message = (Message) msg;

    }

}
