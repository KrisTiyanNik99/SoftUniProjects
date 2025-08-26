package picking.repositories;

import picking.entities.places.Place;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PlaceRepository implements Repository<Place> {
    private final Collection<Place> places;

    public PlaceRepository() {
        places = new ArrayList<>();
    }

    @Override
    public Collection<Place> getCollection() {
        return Collections.unmodifiableCollection(places);
    }

    @Override
    public void add(Place entity) {
        // проверка да се направи
        places.add(entity);
    }

    @Override
    public boolean remove(Place entity) {
        return places.remove(entity);
    }

    @Override
    public Place byName(String name) {
        return places.stream()
                .filter(e -> name.equals(e.getName()))
                .findFirst()
                .orElse(null);
    }
}
