package competition.entities.car;

public class Hyundai extends BaseCar {
    private static final int INITIAL_BATTERY_CAPACITY = 60;

    public Hyundai(String model) {
        super(model, INITIAL_BATTERY_CAPACITY);
    }
}
