package athletics.entity.competition;

import athletics.entity.athlete.Athlete;
import athletics.entity.sportsfacility.SportsFacility;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionImpl implements Competition {
    @Override
    public List<Athlete> executeCompetition(SportsFacility sportsFacility) {
        List<Athlete> athletes = sportsFacility.getAthletes().stream().toList();
        boolean isAthleateComplete = false;

        do {
            for (Athlete athlete : athletes) {
                if (athlete.getStamina() > 0) {
                    athlete.compete();
                }

                if (athlete.getPerformance() >= 5) {
                    isAthleateComplete = true;
                    break;
                }
            }

            // athletes.removeIf(e -> e.getStamina() <= 0); !isAthleateComplete && athletes.size() > 0
        } while (!isAthleateComplete);

        return athletes.stream()
                .sorted(Comparator.comparingInt(Athlete::getPerformance).reversed()
                        .thenComparing(Athlete::getName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparingInt(Athlete::getStamina))
                .limit(3)
                .collect(Collectors.toList());
    }
}
