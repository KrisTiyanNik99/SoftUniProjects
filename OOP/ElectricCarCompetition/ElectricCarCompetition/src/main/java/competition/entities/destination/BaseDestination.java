package competition.entities.destination;

import competition.common.ExceptionMessages;
import competition.entities.car.Car;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseDestination implements Destination {
    private String name;
    private int distance;
    private Collection<Car> cars;

    public BaseDestination(String name, int distance) {
        setName(name);
        setDistance(distance);
        cars = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessages.DESTINATION_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException(ExceptionMessages.NEGATIVE_DISTANCE_VALUE);
        }

        this.distance = distance;
    }

    @Override
    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}
