package competition.entities.repositories;

import java.util.Collection;

public interface Repository<T> {

    Collection<T> getCollection();

    void add(T destination);

    boolean remove(T destination);

    T byName(String name);
}
