package competition.entities.destination;

public class Lake extends BaseDestination {
    private static final int REQUIRE_DESTINATION_KILOMETERS = 25;

    public Lake(String name) {
        super(name, REQUIRE_DESTINATION_KILOMETERS);
    }
}
