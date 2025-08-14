package athletics.entity.competition;

import athletics.entity.athlete.Athlete;
import athletics.entity.sportsfacility.SportsFacility;

import java.util.List;

public interface Competition {

    List<Athlete> executeCompetition(SportsFacility sportsFacility);
}
