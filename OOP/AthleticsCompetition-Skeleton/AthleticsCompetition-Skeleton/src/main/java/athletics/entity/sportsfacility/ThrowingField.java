package athletics.entity.sportsfacility;

import athletics.entity.athlete.Athlete;
import athletics.entity.athlete.Thrower;

public class ThrowingField extends BaseSportsFacility {
    private static final int ATHLETE_CAPACITY = 12;

    public ThrowingField(String name) {
        super(name, ATHLETE_CAPACITY);
    }

    @Override
    public boolean isCompatible(Athlete athlete) {
        return athlete instanceof Thrower;
    }
}
