package athletics.entity.sportsfacility;

import athletics.entity.athlete.Athlete;

import java.util.Collection;

public interface SportsFacility {
    void addAthlete(Athlete athlete);

    boolean isCompatible(Athlete athlete);

    Collection<Athlete> getAthletes();

    String getName();
}
