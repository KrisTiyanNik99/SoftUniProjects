package competition.entities.car;

public class VW extends BaseCar {
    private static final int INITIAL_BATTERY_CAPACITY = 50;

    public VW(String model) {
        super(model, INITIAL_BATTERY_CAPACITY);
    }
}
