package edu.northeastern.cs5500.delivery.repository;

import java.util.Collection;
import javax.annotation.Nonnull;
import org.bson.types.ObjectId;

public interface GenericRepository<T> {
    public T get(@Nonnull ObjectId id);

    public T add(@Nonnull T item);

    public T update(@Nonnull T item);

    public T updateByUserName(@Nonnull T item, String name);

    public void delete(@Nonnull ObjectId id);

    public void deleteByUnique(String uniqueId, Object value);

    public Collection<T> getAll();

    public long count();
}
