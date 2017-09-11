package com.spikes2212.prometheus_server.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * A Wrapper object for {@link MongoCollection} that operates only with a specific type of {@link Savable}
 * @param <T> A type of {@link Savable} that the {@link TypedCollection} instance works with
 * @see MongoCollection
 * @author Simon "C" Kharmatsky
 */
public class TypedCollection<T extends Savable> {
    /**
     * The {@link MongoCollection} that the {@link TypedCollection} instance wraps
     */
    private MongoCollection<Document> collection;

    /**
     * A constructor that constructs new instance of {@link TypedCollection}
     * @param collection The {@link MongoCollection} that the {@link TypedCollection} wraps
     */
    public TypedCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    /**
     * A method that insert an instance of {@link T} into the {@link MongoCollection} instance
     * @param object instance of {@link T} to insert into the {@link MongoCollection}
     */
    public void insertOne(T object) {
        collection.insertOne(object.toDocument());
    }

    /**
     * A method that finds one object in the database according to the {@param filter}
     * and returns object of type {@link T}
     * @param filter the filter according to which the object in the database is searched
     * @param type the type that the method will return, usually {@link T}
     * @return returns instance of {@param type} that was parsed from document by {@link Savable#fromDocument(Document)}
     * @throws IllegalAccessException a result of using {@link Class#newInstance()} method
     * @throws InstantiationException a result of using {@link Class#newInstance()} method
     */
    public T findOne(Document filter, Class<T> type) throws IllegalAccessException, InstantiationException {
        FindIterable<Document> iterable = collection.find(filter);
        T t = type.newInstance();
        t.fromDocument(iterable.first());
        return t;
    }

    /**
     * A method that updates object that is found by the {@param filter} with the {@param object}
     * @param filter a {@link Document} according to which the object will be searched in the {@link MongoCollection}
     * @param object the new object that will be written to the {@link MongoCollection}
     */
    public void updateOne(Document filter, T object) {
        collection.updateOne(filter, object.toDocument());
    }

    /**
     * A method that removed object that is found by the {@param filter}
     * @param filter a {@link Document} according to which the object will be searched
     */
    public void removeOne(Document filter) {
        collection.deleteOne(filter);
    }
}
