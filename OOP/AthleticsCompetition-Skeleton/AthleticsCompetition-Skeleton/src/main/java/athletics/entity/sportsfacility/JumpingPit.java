package athletics.entity.sportsfacility;

import athletics.entity.athlete.Athlete;
import athletics.entity.athlete.Jumper;

public class JumpingPit extends BaseSportsFacility {
    private static final int ATHLETE_CAPACITY = 8;

    public JumpingPit(String name) {
        super(name, ATHLETE_CAPACITY);
    }

    @Override
    public boolean isCompatible(Athlete athlete) {
        return athlete instanceof Jumper;
    }
}
