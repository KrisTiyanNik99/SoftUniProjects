package athletics.repository;

import athletics.entity.sportsfacility.SportsFacility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SportsFacilityRepository implements Repository<SportsFacility> {
    private final Collection<SportsFacility> facilities;

    public SportsFacilityRepository() {
        facilities = new ArrayList<>();
    }

    @Override
    public Collection<SportsFacility> getCollection() {
        return Collections.unmodifiableCollection(facilities);
    }

    @Override
    public void add(SportsFacility entity) {
        facilities.add(entity);
    }

    @Override
    public boolean remove(SportsFacility entity) {
        return facilities.remove(entity);
    }

    @Override
    public SportsFacility byName(String name) {
        return facilities.stream()
                .filter(e -> name.equals(e.getName()))
                .findFirst()
                .orElse(null);
    }
}
