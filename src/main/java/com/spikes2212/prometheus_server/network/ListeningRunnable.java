package com.spikes2212.prometheus_server.network;

import com.spikes2212.prometheus_server.database.TypedCollection;
import com.spikes2212.prometheus_server.network.data.Group;
import com.spikes2212.prometheus_server.network.data.User;
import com.spikes2212.prometheus_server.network.message.Message;
import com.spikes2212.prometheus_server.util.JsonUtil;
import com.spikes2212.prometheus_server.util.LogUtil;

import java.io.IOException;
import java.util.Map;

/**
 * A {@link Runnable} that is passed to {@link Connection#listeningThread}
 * This Class is responsible for receiving messages that are sent through the internet and process them.
 *
 * @see java.lang.Runnable
 * @see Connection
 * @see TypedCollection
 * @author Simon "C" Kharmatsky
 */
public class ListeningRunnable implements Runnable {

    private Connection connection;
    private TypedCollection<Group> groupsCollection;
    private TypedCollection<User> usersCollection;

    /**
     * A constructor that constructs {@link ListeningRunnable} using
     * {@link TypedCollection<User>}, {@link TypedCollection<Group>} and {@link Connection} instances
     *
     * @param connection {@link Connection} instance that is used by {@link ListeningRunnable}
     * @param groupsCollection {@link TypedCollection<User>} instance that is used by {@link ListeningRunnable}
     * @param usersCollection {@link TypedCollection<Group>} instance that is used by {@link ListeningRunnable}
     */
    public ListeningRunnable(Connection connection, TypedCollection<Group> groupsCollection,
                             TypedCollection<User> usersCollection) {
        this.connection = connection;
        this.groupsCollection = groupsCollection;
        this.usersCollection = usersCollection;
    }

    /**
     * Run Method, overridden from {@link Runnable#run()}
     * This Method start listening loop for the {@link ListeningRunnable#connection}
     * and process messages received by {@link Connection#input}
     */
    public void run() {
        try {
            String data;
            while ((data = connection.readLine()) != null) {
                Map<String, String> messageMap = JsonUtil.fromJson(data);
                processMessageMap(messageMap);
            }
        } catch (IOException ioe) {
            LogUtil.error("IOE while running main listening loop", "");
            ioe.printStackTrace();
        }
    }

    /**
     * A method that processes message from {@link Map} received by {@link ListeningRunnable#connection}
     * @param messageMap the {@link Map} instance received from {@link ListeningRunnable#connection}
     */
    private void processMessageMap(Map<String, String> messageMap) {
        Message msg = null;
        // processing message from messageMap

        if (msg != null) {
            msg.fromMap(messageMap);
            msg.process(usersCollection, groupsCollection, connection);
        }
    }

}
