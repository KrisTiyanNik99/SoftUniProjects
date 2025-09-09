package competition.entities.competition;

import competition.entities.car.Car;
import competition.entities.destination.Destination;

import java.util.Collection;

public interface Competition {

    void startVoyage(Destination destination, Collection<Car> cars);
}
