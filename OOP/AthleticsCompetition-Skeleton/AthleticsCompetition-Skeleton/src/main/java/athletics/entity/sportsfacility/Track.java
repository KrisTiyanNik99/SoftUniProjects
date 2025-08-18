package athletics.entity.sportsfacility;

import athletics.entity.athlete.Athlete;
import athletics.entity.athlete.Runner;

public class Track extends BaseSportsFacility {
    private static final int ATHLETE_CAPACITY = 6;
    public Track(String name) {
        super(name, ATHLETE_CAPACITY);
    }

    @Override
    public boolean isCompatible(Athlete athlete) {
        return athlete instanceof Runner;
    }
}
