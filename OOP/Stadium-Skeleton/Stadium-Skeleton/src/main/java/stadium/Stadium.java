package stadium;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class Stadium {

    private static final String INVALID_STADIUM_NAME = "Invalid stadium name!";
    private static final String INVALID_CAPACITY = "Invalid capacity!";
    private static final String NO_MORE_SPACE = "No more space in the stadium!";
    private static final String ATHLETE_EXIST = "This athlete is already in the stadium!";
    private static final int ZERO_CAPACITY = 0;

    private String name;
    private int capacity;
    private Collection<Athlete> athletes;

    public Stadium(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.athletes = new ArrayList<>();
    }

    public int getCount() {
        return this.athletes.size();
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void addAthlete(Athlete athlete) {
        if (this.athletes.size() == this.capacity) {
            throw new IllegalArgumentException(NO_MORE_SPACE);
        }

        boolean exists = this.athletes.stream()
                .anyMatch(a -> a.getName().equals(athlete.getName()));

        if (exists) {
            throw new IllegalArgumentException(ATHLETE_EXIST);
        }

        this.athletes.add(athlete);
    }

    public Athlete getAthlete(String name) {
        return this.athletes.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Athlete getFittestAthlete() {
        return this.athletes.stream()
                .max(Comparator.comparing(Athlete::getStamina))
                .orElse(null);
    }

    public boolean removeAthlete(String name) {
        Athlete athlete = getAthlete(name);
        return this.athletes.remove(athlete);
    }

    private void setCapacity(int capacity) {
        if (capacity < ZERO_CAPACITY) {
            throw new IllegalArgumentException(INVALID_CAPACITY);
        }
        this.capacity = capacity;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(INVALID_STADIUM_NAME);
        }
        this.name = name;
    }
}


