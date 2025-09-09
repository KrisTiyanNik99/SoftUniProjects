package competition.entities.repositories;

import competition.entities.destination.Destination;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DestinationRepository implements Repository<Destination> {
    private final Map<String, Destination> destinations;

    public DestinationRepository() {
        destinations = new LinkedHashMap<>();
    }

    @Override
    public Collection<Destination> getCollection() {
        return Collections.unmodifiableCollection(destinations.values());
    }

    @Override
    public void add(Destination destination) {
        destinations.put(destination.getName(), destination);
    }

    @Override
    public boolean remove(Destination destination) {
        return destinations.remove(destination.getName()) != null;
    }

    @Override
    public Destination byName(String name) {
        return destinations.get(name);
    }
}
