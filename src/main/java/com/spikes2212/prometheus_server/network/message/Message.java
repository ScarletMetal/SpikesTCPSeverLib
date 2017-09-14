package com.spikes2212.prometheus_server.network.message;

import com.spikes2212.prometheus_server.database.TypedCollection;
import com.spikes2212.prometheus_server.network.Connection;
import com.spikes2212.prometheus_server.network.data.Group;
import com.spikes2212.prometheus_server.network.data.User;

/**
 * A class for basic Message that is passed through the network from the client to the server and vice versa
 * @see Connection
 * @see com.spikes2212.prometheus_server.network.ListeningRunnable
 * @author Simon "C" Kharmatsky
 */
import java.util.Map;

public abstract class Message {
    /**
     * A string that contains information about the type of the message (commands that server has to execute)
     */
    public String id;

    /**
     * This method is made to get all the information from the received from {@link Connection} instance
     * an parse it to {@link Message} instance
     * @param map Map that contains all the information received from the client
     */
    public abstract void fromMap(Map<String, String> map);

    /**
     * This method is made to process the information received through the network, manipulates the databases
     * and sends a message back to the client.
     * @param users {@link TypedCollection} of {@link User}
     * @param group {@link TypedCollection} of {@link Group}
     * @param connection {@link Connection} instance through which the response {@link Message} will be sent
     */
    public abstract void process(TypedCollection<User> users,
                                 TypedCollection<Group> group, Connection connection);
}
