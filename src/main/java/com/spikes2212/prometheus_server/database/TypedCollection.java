package com.spikes2212.prometheus_server.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class TypedCollection<T extends Savable> {
    private MongoCollection<Document> collection;

    public TypedCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void insertOne(T object) {
        collection.insertOne(object.toDocument());
    }

    public T findOne(Document filter, Class<T> type) throws IllegalAccessException, InstantiationException {
        FindIterable<Document> iterable = collection.find(filter);
        T t = type.newInstance();
        t.fromDocument(iterable.first());
        return t;
    }

    public void updateOne(Document filter, T object) {
        collection.updateOne(filter, object.toDocument());
    }

    public void removeOne(Document filter) {
        collection.deleteOne(filter);
    }


}
