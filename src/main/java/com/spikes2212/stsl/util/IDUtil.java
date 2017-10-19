package com.spikes2212.stsl.util;

import com.spikes2212.stsl.Constants;
import com.spikes2212.stsl.database.TypedCollection;
import org.bson.Document;

import java.util.Random;

public class IDUtil {
    public static String generateIDForCollection(int idLength, TypedCollection collection)
            throws InstantiationException,  IllegalAccessException {
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
