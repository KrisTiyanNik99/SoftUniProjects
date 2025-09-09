package competition.entities.car;

public class Tesla extends BaseCar {
    private static final int INITIAL_BATTERY_CAPACITY = 85;

    public Tesla(String model) {
        super(model, INITIAL_BATTERY_CAPACITY);
    }
}
