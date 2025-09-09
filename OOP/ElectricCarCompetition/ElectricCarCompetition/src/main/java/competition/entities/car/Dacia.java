package competition.entities.car;

public class Dacia extends BaseCar {
    private static final int INITIAL_BATTERY_CAPACITY = 30;

    public Dacia(String model) {
        super(model, INITIAL_BATTERY_CAPACITY);
    }
}
