package competition.entities.destination;

import competition.entities.car.Car;

import java.util.Collection;

public interface Destination {

    Collection<Car> getCars();

    String getName();

    int getDistance();
}
