package athletics.entity.sportsfacility;

import athletics.entity.athlete.Athlete;
import athletics.entity.athlete.Thrower;

public class ThrowingField extends BaseSportsFacility {
    public ThrowingField(String name) {
        super(name, 12);
    }

    @Override
    public boolean isCompatible(Athlete athlete) {
        return athlete instanceof Thrower;
    }
}
