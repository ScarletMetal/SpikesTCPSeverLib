package com.spikes2212.stsl.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * <p>A Wrapper object for {@link MongoCollection} that operates only with a specific type of {@link Savable}</p>
 * @param <T> A type of {@link Savable} that the {@link TypedCollection} instance works with
 */
public class TypedCollection<T extends Savable> {
    private MongoCollection<Document> collection;

    /**
     * <p>
     *     A constructor that constructs new instance of {@link TypedCollection},
     *     using a {@link MongoCollection} instance
     * </p>
     * @param collection The {@link MongoCollection} that the {@link TypedCollection} wraps
     */
    public TypedCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    /**
     * <p>
     *     This method inserts a given {@link T} instance in to {@link TypedCollection#collection}
     * </p>
     * @param object instance of {@link T} to insert into the {@link MongoCollection}
     */
    public void insertOne(T object) {
        collection.insertOne(object.toDocument());
    }

    /**
     * <p>
     *     This method finds document in {@link TypedCollection#collection}
     *     and returns an instance of {@link T}
     * </p>
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
     * <p>
     *     This method updates a {@link Document} instance in {@link TypedCollection#collection}
     *     that is found by a given filter of type {@link Document}
     *     with a given instance of {@link T}, parsed with {@link Savable#toDocument()}
     * </p>
     * @param filter a {@link Document} according to which the object will be searched in the {@link MongoCollection}
     * @param object the new object that will be written to the {@link MongoCollection}
     */
    public void updateOne(Document filter, T object) {
        collection.updateOne(filter, object.toDocument());
    }

    /**
     * <p>
     *     This method removes a {@link Document} that is found by the given filter of type {@link Document}
     *     from {@link TypedCollection#collection}
     * </p>
     * @param filter a {@link Document} according to which the object will be searched
     */
    public void removeOne(Document filter) {
        collection.deleteOne(filter);
    }
}
