package competition.core;

//TODO - Implement all the methods

import competition.common.ConstantMessages;
import competition.common.ExceptionMessages;
import competition.entities.car.*;
import competition.entities.competition.Competition;
import competition.entities.competition.CompetitionImpl;
import competition.entities.destination.Destination;
import competition.entities.destination.Lake;
import competition.entities.destination.Mountain;
import competition.entities.destination.SeaSide;
import competition.entities.repositories.DestinationRepository;
import competition.entities.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller {
    private final Repository<Destination> destinationRepository;

    public ControllerImpl() {
        destinationRepository = new DestinationRepository();
    }

    @Override
    public String addDestination(String destinationType, String destinationName) {
        Destination destination;
        switch (destinationType) {
            case "Lake" -> destination = new Lake(destinationName);
            case "Mountain" -> destination = new Mountain(destinationName);
            case "SeaSide" -> destination = new SeaSide(destinationName);
            default -> throw new IllegalArgumentException(ExceptionMessages.INVALID_DESTINATION);
        }

        Destination isExisted = destinationRepository.byName(destinationName);
        if (isExisted != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_DESTINATION);
        }

        destinationRepository.add(destination);
        return String.format(ConstantMessages.DESTINATION_ADDED, destinationType, destinationName);
    }

    @Override
    public String addCar(String destinationName, String carBrand, String carModel) {
        Destination destination = destinationRepository.byName(destinationName);
        if (destination == null) {
            throw new NullPointerException(ExceptionMessages.NON_EXISTING_DESTINATION);
        }

        Car isCarExist = getCarByBrandAndModel(destination, carBrand, carModel);
        if (isCarExist != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_CAR_BRAND_AND_MODEL);
        }

        Car car;
        switch (carBrand) {
            case "Dacia" -> car = new Dacia(carModel);
            case "Hyundai" -> car = new Hyundai(carModel);
            case "Tesla" -> car = new Tesla(carModel);
            case "VW" -> car = new VW(carModel);
            default -> throw new IllegalArgumentException(ExceptionMessages.INVALID_CAR);
        }

        destination.getCars().add(car);
        return String.format(ConstantMessages.CAR_ADDED, carBrand, carModel);
    }

    @Override
    public String reachDestination(String destinationName) {
        Destination destination = destinationRepository.byName(destinationName);

        Competition competition = new CompetitionImpl();
        competition.startVoyage(destination, destination.getCars());
        List<Car> unfinishedCars = getUnfinishedCars(destination, destination.getCars());

        return String.format(ConstantMessages.VOYAGE_OVER, destinationName, unfinishedCars.size());
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Destination currentDestination : destinationRepository.getCollection()) {
            sb.append(String.format(ConstantMessages.CARS_TOOK_PART, currentDestination.getName())).append(System.lineSeparator());
            for (Car car : currentDestination.getCars()) {
                sb.append(String.format(ConstantMessages.FINAL_CAR_INFO,
                        car.getClass().getSimpleName(),
                        car.getModel(),
                        car.getBatteryCapacity(),
                        car.getMileage())).append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    private List<Car> getUnfinishedCars(Destination destination, Collection<Car> cars) {
        List<Car> unfinishedCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getMileage() < destination.getDistance() && car.getBatteryCapacity() < 15) {
                unfinishedCars.add(car);
            }
        }

        return unfinishedCars;
    }

    private Car getCarByBrandAndModel(Destination destination, String carBrand, String carModel) {
        return destination.getCars()
                .stream()
                .filter(e -> carBrand.equals(e.getClass().getSimpleName()))
                .filter(e -> carModel.equals(e.getModel()))
                .findFirst()
                .orElse(null);
    }

//    private Destination getDestinationByName(String destinationName) {
//        return destinationRepository.getCollection()
//                .stream()
//                .filter(e -> destinationName.equals(e.getName()))
//                .findFirst()
//                .orElse(null);
//    }
}
