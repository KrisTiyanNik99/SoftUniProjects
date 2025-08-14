package athletics.entity.sportsfacility;

import athletics.entity.athlete.Athlete;
import athletics.entity.athlete.Jumper;

public class JumpingPit extends BaseSportsFacility {
    public JumpingPit(String name) {
        super(name, 8);
    }

    @Override
    public boolean isCompatible(Athlete athlete) {
        return athlete instanceof Jumper;
    }
}
