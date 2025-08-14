package athletics.entity.sportsfacility;

import athletics.entity.athlete.Athlete;
import athletics.entity.athlete.Runner;

public class Track extends BaseSportsFacility {
    public Track(String name) {
        super(name, 6);
    }

    @Override
    public boolean isCompatible(Athlete athlete) {
        return athlete instanceof Runner;
    }
}
