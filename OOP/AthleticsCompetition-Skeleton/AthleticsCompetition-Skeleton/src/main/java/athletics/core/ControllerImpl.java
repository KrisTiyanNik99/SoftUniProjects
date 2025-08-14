package athletics.core;

import athletics.entity.athlete.Athlete;
import athletics.entity.athlete.Jumper;
import athletics.entity.athlete.Runner;
import athletics.entity.athlete.Thrower;
import athletics.entity.competition.Competition;
import athletics.entity.competition.CompetitionImpl;
import athletics.entity.sportsfacility.JumpingPit;
import athletics.entity.sportsfacility.SportsFacility;
import athletics.entity.sportsfacility.ThrowingField;
import athletics.entity.sportsfacility.Track;
import athletics.repository.Repository;
import athletics.repository.SportsFacilityRepository;

import java.util.List;

import static athletics.common.ConstantMessages.*;
import static athletics.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final Repository<SportsFacility> sportsFacilityRepository;
    private final Competition competition;

    public ControllerImpl() {
        this.sportsFacilityRepository = new SportsFacilityRepository();
        competition = new CompetitionImpl();
    }

    //TODO - Implement all methods
    @Override
    public String addSportsFacility(String sportsFacilityName, String sportsFacilityType) {
        SportsFacility currentFacility = sportsFacilityRepository.byName(sportsFacilityName);
        if (currentFacility != null) {
            throw new IllegalArgumentException(DUPLICATE_FACILITY);
        }

        SportsFacility facility;
        switch (sportsFacilityType) {
            case "Track" -> facility = new Track(sportsFacilityName);
            case "JumpingPit" -> facility = new JumpingPit(sportsFacilityName);
            case "ThrowingField" -> facility = new ThrowingField(sportsFacilityName);
            default -> throw new IllegalArgumentException("Invalid SportsFacility.");
        }

        sportsFacilityRepository.add(facility);
        return String.format(SPORTS_FACILITY_ADDED, sportsFacilityType);
    }

    @Override
    public String addAthlete(String sportsFacilityName, String athleteType, String athleteName) {
        SportsFacility sportsFacility = sportsFacilityRepository.byName(sportsFacilityName);

        Athlete athlete;
        switch (athleteType) {
            case "Runner" -> athlete = new Runner(athleteName);
            case "Jumper" -> athlete = new Jumper(athleteName);
            case "Thrower" -> athlete = new Thrower(athleteName);
            default -> throw new IllegalArgumentException(INVALID_ATHLETE_TYPE);
        }

        Athlete currentAthlete = sportsFacility.getAthletes().stream()
                .filter(e -> e.getName().equals(athleteName))
                .findFirst()
                .orElse(null);
        if (currentAthlete != null) {
            throw new IllegalArgumentException(String.format(ATHLETE_ALREADY_EXISTS, athleteName));
        }

        sportsFacility.addAthlete(athlete);
        return String.format(ATHLETE_ADDED, athleteType, sportsFacility.getName());
    }

    @Override
    public String trainAthlete(String sportsFacilityName, String athleteName) {
        SportsFacility sportsFacility = sportsFacilityRepository.byName(sportsFacilityName);
        if (sportsFacility == null) {
            throw new NullPointerException(String.format(FACILITY_NOT_FOUND, sportsFacilityName));
        }

        Athlete currentAthlete = sportsFacility.getAthletes().stream()
                .filter(e -> e.getName().equals(athleteName))
                .findFirst()
                .orElse(null);
        if (currentAthlete == null) {
            throw new NullPointerException(String.format(ATHLETE_NOT_FOUND, athleteName, sportsFacility.getName()));
        }

        currentAthlete.train();
        return String.format(ATHLETE_TRAINED, currentAthlete.getName(), currentAthlete.getStamina());
    }

    @Override
    public String startCompetition(String sportsFacilityName) {
        SportsFacility sportsFacility = sportsFacilityRepository.byName(sportsFacilityName);
        if (sportsFacility == null) {
            throw new NullPointerException(String.format(FACILITY_NOT_FOUND, sportsFacilityName));
        }

        List<Athlete> athletes = competition.executeCompetition(sportsFacility);
        return String.format(COMPETITION_COMPLETED, sportsFacilityName) + formatedList(athletes);
    }

    @Override
    public String getStatistics() {
        List<SportsFacility> sportsFacilities = sportsFacilityRepository.getCollection().stream().toList();
        StringBuilder stringBuilder = new StringBuilder();

        for (SportsFacility sportsFacility : sportsFacilities) {
            List<Athlete> athletes = sportsFacility.getAthletes()
                    .stream()
                    .filter(e -> e.getPerformance() > 0)
                    .toList();

            if (!athletes.isEmpty()) {
                stringBuilder.append(String.format(STATISTICS_ENTRY,
                        sportsFacility.getName(),
                        sportsFacility.getClass().getSimpleName()));

                for (Athlete athlete : athletes) {
                    stringBuilder.append("Name: ").append(athlete.getName()).append(System.lineSeparator());
                    stringBuilder.append("Stamina left: ").append(athlete.getStamina()).append(System.lineSeparator());
                    stringBuilder.append("Performance: ").append(athlete.getPerformance()).append(System.lineSeparator());
                }
            }
        }

        return stringBuilder.toString().trim();


//        List<SportsFacility> sportsFacilities = sportsFacilityRepository.getCollection().stream().toList();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (SportsFacility sportsFacility : sportsFacilities) {
//            List<Athlete> athletes = sportsFacility.getAthletes()
//                    .stream()
//                    .filter(e -> e.getPerformance() > 0)
//                    .toList();
//
//            stringBuilder.append(String.format(STATISTICS_ENTRY, sportsFacility.getName(), sportsFacility.getClass().getSimpleName()));
//            for (Athlete athlete : athletes) {
//                stringBuilder.append("Name: ").append(athlete.getName()).append(System.lineSeparator());
//                stringBuilder.append("Stamina left: ").append(athlete.getStamina()).append(System.lineSeparator());
//                stringBuilder.append("Performance: ").append(athlete.getPerformance()).append(System.lineSeparator());
//            }
//        }
//
//        return stringBuilder.toString();
    }


    private String formatedList(List<Athlete> athletes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < athletes.size(); i++) {
            sb.append(i + 1).append(" place: ").append(athletes.get(i).getName()).append(" - ")
                    .append(athletes.get(i).getPerformance()).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
