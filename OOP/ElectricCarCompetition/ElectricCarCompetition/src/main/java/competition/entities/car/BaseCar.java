package competition.entities.car;

import competition.common.ExceptionMessages;

public abstract class BaseCar implements Car {
    private static final int REDUCED_BATTERY_CAPACITY = 15;
    private static final int INCREASED_MILEAGE = 25;

    private String model;
    private int batteryCapacity;
    private int mileage;

    public BaseCar(String name, int batteryCapacity) {
        setModel(name);
        setBatteryCapacity(batteryCapacity);
    }

    @Override
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model.isEmpty() || model.isBlank()) {
            throw new NullPointerException(ExceptionMessages.CAR_MODEL_NULL_OR_EMPTY);
        }

        this.model = model;
    }

    @Override
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = Math.max(batteryCapacity, 0);
    }

    @Override
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public void drive() {
        setBatteryCapacity(getBatteryCapacity() - REDUCED_BATTERY_CAPACITY);
        setMileage(getMileage() + INCREASED_MILEAGE);
    }
}
