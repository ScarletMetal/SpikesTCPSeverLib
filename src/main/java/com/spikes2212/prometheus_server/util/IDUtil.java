package com.spikes2212.prometheus_server.util;

import com.spikes2212.prometheus_server.Constants;
import com.spikes2212.prometheus_server.database.TypedCollection;
import org.bson.Document;

import java.util.Random;

public class IDUtil {
    public static String generateIDForCollection(int idLength, TypedCollection collection)
            throws InstantiationException, IllegalAccessException {
        String id;
        StringBuilder builder = new StringBuilder();

        do {
            for (int i = 0; i < idLength; i++) {
                int randomID = new Random().nextInt(
                        (Constants.ID.MAX_ID_CODE - Constants.ID.MIN_ID_CODE) + 1)
                        + Constants.ID.MIN_ID_CODE;
                builder.append((char) randomID);
            }
            id = builder.toString();
        } while (collection.findOne(new Document("id", id),
                collection.getClass().getGenericSuperclass().getClass()) == null);

        return id;
    }
}
