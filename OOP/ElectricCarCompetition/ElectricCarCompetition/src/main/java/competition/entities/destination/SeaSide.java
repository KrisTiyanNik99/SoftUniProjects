package competition.entities.destination;

public class SeaSide extends BaseDestination {
    private static final int REQUIRE_DESTINATION_KILOMETERS = 80;

    public SeaSide(String name) {
        super(name, REQUIRE_DESTINATION_KILOMETERS);
    }
}
