package com.spikes2212.prometheus_server.database;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.sun.istack.internal.Nullable;

public class TypedCollection<T extends Savable> {

    private DBCollection collection;

    public TypedCollection(DBCollection collection) {
        this.collection = collection;
    }

    public void insert(T object) {
        collection.insert(object.toDocument());
    }

    @Nullable public BasicDBObject findFirst(BasicDBObject query) {

        Cursor cursor = collection.find(query);
        BasicDBObject object = (BasicDBObject) cursor.next();

        for (String queryKey : query.keySet()) {
            if (object.get(queryKey) == null) return null;
            if (!object.get(queryKey).equals(query.get(queryKey))) return null;
        }
        return object;
    }

    public void update(BasicDBObject query, T update) {
        collection.update(query, update.toDocument());
    }
}
