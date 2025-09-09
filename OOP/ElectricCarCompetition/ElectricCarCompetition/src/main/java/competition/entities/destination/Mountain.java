package competition.entities.destination;

public class Mountain extends BaseDestination {
    private static final int REQUIRE_DESTINATION_KILOMETERS = 60;

    public Mountain(String name) {
        super(name, REQUIRE_DESTINATION_KILOMETERS);
    }
}
