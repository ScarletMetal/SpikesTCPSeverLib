package com.spikes2212.prometheus_server.database;

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

}
