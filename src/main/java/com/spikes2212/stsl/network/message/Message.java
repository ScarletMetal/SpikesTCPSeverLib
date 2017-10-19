package com.spikes2212.stsl.network.message;

import com.spikes2212.stsl.database.TypedCollection;
import com.spikes2212.stsl.network.Connection;

import java.util.Map;

/**
 * <p>A class for basic Message that is passed through the network from the client to the server and vice versa</p>
 * @see Connection
 * @author Simon "C" Kharmatsky
 */
public abstract class Message {
    /**
     * A string that contains information about the type of the message (commands that server has to execute)
     */
    public String id;

    /**
     * <p>
     * This method receives the information needed to build the {@link Message} instance from a
     * {@link Map} instance
     * </p>
     * @param map Map that contains all the information received from the client
     */
    public abstract void fromMap(Map<String, String> map);

    /**
     * <p>
     * This method is made to process the information received through the network, manipulates the databases
     * and sends a message back to the client.
     * This method is written in a way that allows any type that extends from {@link Message} to implements it,
     * thus allowing to process all the message types in the same way.
     * </p>
     */

    public abstract void process();
}
