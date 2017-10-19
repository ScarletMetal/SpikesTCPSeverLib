package com.spikes2212.stsl.network;

import com.spikes2212.stsl.database.TypedCollection;
import com.spikes2212.stsl.network.data.Group;
import com.spikes2212.stsl.network.data.User;
import com.spikes2212.stsl.network.message.Message;
import com.spikes2212.stsl.util.JsonUtil;
import com.spikes2212.stsl.util.LogUtil;

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
     * <p>
     * A constructor that constructs {@link ListeningRunnable} using
     * {@link TypedCollection<User>}, {@link TypedCollection<Group>} and {@link Connection} instances
     * </p>
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
     * <p>
     *     This method contains the main IO loop for {@link ListeningRunnable#connection}.
     *     Using a loop to receive information from the {@link ListeningRunnable#connection},
     *     it parses the received information from JSON and passes it to
     *     {@link ListeningRunnable#processMessageMap(Map)}
     * </p>
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
     * <p>
     * This method builds an instance of {@link Message} from a given {@link Map},
     * and then processes it via {@link Message#process(TypedCollection, TypedCollection, Connection)}
     * </p>
     * @param messageMap the {@link Map} instance received from {@link ListeningRunnable#connection}
     */
    private void processMessageMap(Map<String, String> messageMap) {
        Message msg = null;

        if (msg != null) {
            msg.fromMap(messageMap);
            msg.process(usersCollection, groupsCollection, connection);
        }
    }

}
