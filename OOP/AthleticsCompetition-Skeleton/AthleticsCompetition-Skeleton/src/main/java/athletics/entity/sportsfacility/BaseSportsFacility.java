package athletics.entity.sportsfacility;

import athletics.entity.athlete.Athlete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static athletics.common.ExceptionMessages.*;

public abstract class BaseSportsFacility implements SportsFacility {
    private String name;
    private int capacity;
    private final Collection<Athlete> athletes;

    public BaseSportsFacility(String name, int capacity) {
        setName(name);
        setCapacity(capacity);
        athletes = new ArrayList<>();
    }

    public void setName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException(INVALID_FACILITY_NAME);
        }

        this.name = name;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(NEGATIVE_CAPACITY);
        }

        this.capacity = capacity;
    }

    @Override
    public void addAthlete(Athlete athlete) {
        if (!isCompatible(athlete)) {
            throw new IllegalArgumentException(INCOMPATIBLE_ATHLETE);
        }

        if (athletes.size() >= capacity) {
            throw new IllegalArgumentException(NO_CAPACITY);
        }

        athletes.add(athlete);
    }

    @Override
    public Collection<Athlete> getAthletes() {
        return Collections.unmodifiableCollection(athletes);
    }

    @Override
    public String getName() {
        return name;
    }
}
