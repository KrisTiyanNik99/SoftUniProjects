package athletics.core;

public interface Controller {

    String addSportsFacility(String sportsFacilityName, String sportsFacilityType);

    String addAthlete(String sportsFacilityName, String athleteType , String athleteName);

    String trainAthlete (String	sportsFacilityName, String athleteName);

    String startCompetition(String sportsFacilityName);
    String getStatistics();
}
