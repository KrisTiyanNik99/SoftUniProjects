package competition.entities.competition;

import competition.entities.car.Car;
import competition.entities.destination.Destination;

import java.util.Collection;

public class CompetitionImpl implements Competition {
    private static final int REQUIRED_BATTERY_CAPACITY = 15;

    public CompetitionImpl() {
    }

    @Override
    public void startVoyage(Destination destination, Collection<Car> cars) {
        for (Car car : destination.getCars()) {
            while (car.getMileage() < destination.getDistance() && car.getBatteryCapacity() >= REQUIRED_BATTERY_CAPACITY) {
                car.drive();
            }
        }
    }
}
